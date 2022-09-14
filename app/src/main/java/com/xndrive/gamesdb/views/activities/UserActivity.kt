package com.xndrive.gamesdb.views.activities

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.audiofx.Equalizer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.xndrive.gamesdb.R
import com.xndrive.gamesdb.databinding.ActivityUserBinding
import com.xndrive.gamesdb.databinding.ModalDialogImagePickBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private var userActivityBinding: ActivityUserBinding? = null

    companion object{
        val CAMERA_CODE = 111
        val GALLERY_CODE = 112
        val IMAGE_SUFFIX = "gamesdb"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userActivityBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userActivityBinding!!.root)

        determine()
    }

    fun determine() {
        with(userActivityBinding!!){
            this.activityUserUbahFotoBtn.setOnClickListener(this@UserActivity)
        }
    }
    

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.activity_user_ubah_foto_btn -> {
//                Toast.makeText(applicationContext, "klik", Toast.LENGTH_SHORT).show()
                modalImagePick()
            }
        }
    }

    fun modalImagePick() {
        val modalDialog = Dialog(this)
        val modalDialogImagePickBinding = ModalDialogImagePickBinding.inflate(layoutInflater)
        modalDialog.setContentView(modalDialogImagePickBinding.root)
        
        modalDialogImagePickBinding.modalDialogImagePickCamera.setOnClickListener {
            requestCameraPermission()
            modalDialog.dismiss()
        }
        modalDialogImagePickBinding.modalDialogImagePickGallery.setOnClickListener {
            requestGalleryPermission()
            modalDialog.dismiss()
        }
        
        modalDialog.show()
    }

    fun requestCameraPermission() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener{
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                if (p0!!.areAllPermissionsGranted()){
//                    Toast.makeText(this@UserActivity, "permission sudah diperbolehkan", Toast.LENGTH_SHORT).show()
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(intent, CAMERA_CODE)
                } else {
                    askForCameraPermission()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                //alert dialog
                askForCameraPermission()
            }

        }).check()
    }

    fun requestGalleryPermission(){
        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener{
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                Toast.makeText(this@UserActivity, "buka galeri", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY_CODE)
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(this@UserActivity, "Anda tidak mengijinkan aplikasi menggunakan fitur galeri.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                askForGalleryPermission()
            }

        }).onSameThread().check()
    }
    fun askForGalleryPermission() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("Anda belum mengijinkan aplikasi untuk menggunakan fitur galeri. silahkan menuju pengaturan.")
        alertDialog.setPositiveButton("Pergi ke Pengaturan", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }

        })
        alertDialog.setNegativeButton("Batalkan", object :DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0!!.dismiss()
            }

        })
        alertDialog.show()
    }

    fun askForCameraPermission(){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("Anda belum mengijinkan aplikasi untuk menggunakan fitur kamera. Silahkan menuju pengaturan.")
        alertDialog.setPositiveButton("Pergi Ke Pengaturan", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
//                Toast.makeText(this@UserActivity, "klik", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }

        })
        alertDialog.setNegativeButton("Batalkan", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0!!.dismiss()
            }

        })
        alertDialog.show()
    }

    fun saveImageToInternal(bitmap: Bitmap):String{
        var file = this.getDir(IMAGE_SUFFIX, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}_gamesdb.jpg")

        try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            stream.flush()
            stream.close()
        } catch (e : IOException){
            e.printStackTrace()
        }
//        Log.d("gamesdb-log","${file.absolutePath}")
        Toast.makeText(this, "${file.absolutePath}", Toast.LENGTH_SHORT).show()
        return file.absolutePath
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK){
            if (requestCode== CAMERA_CODE){
                val bitmap = data!!.extras!!.get("data") as Bitmap
                Glide.with(this).load(bitmap)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("glide-error","glide image loading error")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val bitmap = resource!!.toBitmap()
                            saveImageToInternal(bitmap)
                            return false
                        }
                    })
                    .into(userActivityBinding!!.activityUserImageview)

            }else if (requestCode == GALLERY_CODE){
                val uriPhoto = data!!.data
                Glide.with(this).load(uriPhoto)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("glide-error","glide image loading error")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val bitmap = resource!!.toBitmap()
                            saveImageToInternal(bitmap)
                            return false
                        }
                    }).into(userActivityBinding!!.activityUserImageview)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}