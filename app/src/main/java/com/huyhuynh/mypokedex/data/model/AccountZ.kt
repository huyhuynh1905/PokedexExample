import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "accountz_az")
data class AccountZ constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idlocal")
    @SerializedName("idlocal")
    var idlocal: Int?,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String,

    @ColumnInfo(name = "imageurl")
    @SerializedName("imageurl")
    var imageurl: String,

    @ColumnInfo(name = "xdescription")
    @SerializedName("xdescription")
    var xdescription: String
)