// Classe MainActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.list_users.DBHelper
import com.example.list_users.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)
        val listUtilizadores = db.utilizadorListSelectAll()

        
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUtilizadores)
        binding.listUsers.adapter = adapter
    }
}
