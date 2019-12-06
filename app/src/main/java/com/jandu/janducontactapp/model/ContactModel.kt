package com.jandu.janducontactapp.model

import android.graphics.Bitmap
import android.net.Uri


data class ContactModel(var id: String? = null,
                        var name: String? = null,
                        var mobileNumber: String? = null,
                        var photo: Bitmap? = null,
                        var photoURI: Uri? = null)