package de.fp.nfc_enable

import android.content.Context
import android.content.Intent
import android.nfc.NfcManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fp.nfc_enable.ui.theme.NFCEnableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // until this is not available, should come in Android 16 https://developer.android.com/reference/android/nfc/NfcAdapter#enable()
        // the app will directly jump into the nfc settings
        var intent = Intent(Settings.ACTION_NFC_SETTINGS);
        startActivity(intent);

        finish()
//        setContent {
//            NFCEnableTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
//                    var nfcAdapter  = nfcManager.defaultAdapter;
//                    var isNfcEnabled : Boolean by remember { mutableStateOf(nfcAdapter?.isEnabled ?: false) };
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(20.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ){
//                        Text("NFC Enabler");
//
//                        // this will later be used
//                        Box(
//                            modifier = Modifier
//                                .size(20.dp)
//                                .background(
//                                    color = if (isNfcEnabled) Color.Green else Color.Red,
//                                    shape = CircleShape
//                                )
//                        )
//                        Button(onClick = {
//                            nfcOn();
//                        }) {
//                            Text(text = "Enable NFC");
//                        }
//                        Button(onClick = {
//                            nfcOff();
//                        }) {
//                            Text(text = "Disable NFC");
//                        }
//                    }
//                }
//            }
//        }
    }
    private fun nfcOn(){
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        var nfcAdapter  = nfcManager.defaultAdapter;
        if (!nfcAdapter.isEnabled){
            var intent = Intent(Settings.ACTION_NFC_SETTINGS);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "NFC is already enabled", Toast.LENGTH_SHORT).show();
        }
    }
    private fun nfcOff(){
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        var nfcAdapter  = nfcManager.defaultAdapter;
        if (nfcAdapter.isEnabled){
            var intent = Intent(Settings.ACTION_NFC_SETTINGS);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "NFC is already disabled", Toast.LENGTH_SHORT).show();
        }
    }
}
