package solutions.laxmi.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import solutions.laxmi.lsnoti.LsSnackbarHost
import solutions.laxmi.lsnoti.rememberLsSnackbarHostState
import solutions.laxmi.sample.ui.theme.LsnotiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LsnotiTheme {
                var showSnackbar by remember { mutableStateOf(false) }

                val lsState = rememberLsSnackbarHostState()

                LaunchedEffect(Unit) {
                    lsState.show("Hello from LsNoti!")
                }

                Scaffold(
                    snackbarHost = { LsSnackbarHost(state = lsState) }
                ){i->
                    Box(modifier = Modifier.padding(i)){
                        Text("Hello")
                    }
                }
            }
        }
    }
}
