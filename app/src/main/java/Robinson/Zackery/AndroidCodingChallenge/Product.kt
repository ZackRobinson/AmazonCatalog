package Robinson.Zackery.AndroidCodingChallenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val title: String, val author: String?, val imageURL: String?): Parcelable
