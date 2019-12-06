package com.jandu.janducontactapp.di

import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import com.jandu.janducontactapp.model.ContactModel
import dagger.Module
import dagger.Provides
import java.io.InputStream
import javax.inject.Singleton


@Module
class ContactModule {

    @Provides
    @Singleton
    fun getContacts(ctx: Context): List<ContactModel>? {
        val list: MutableList<ContactModel> = ArrayList()
        val contentResolver = ctx.contentResolver
        val cursor =
            contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val cursorInfo = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    val inputStream: InputStream? =
                        ContactsContract.Contacts.openContactPhotoInputStream(
                            ctx.contentResolver,
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
                        )
                    val person: Uri =
                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
                    val pURI: Uri = Uri.withAppendedPath(
                        person,
                        ContactsContract.Contacts.Photo.CONTENT_DIRECTORY
                    )
                    var photo: Bitmap? = null
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream)
                    }
                    while (cursorInfo!!.moveToNext()) {
                        val info = ContactModel()
                        info.id = id
                        info.name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        info.mobileNumber =
                            cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        info.photo = photo
                        info.photoURI = pURI
                        list.add(info)
                    }
                    cursorInfo.close()
                }
            }
            cursor.close()
        }
        return list
    }

}