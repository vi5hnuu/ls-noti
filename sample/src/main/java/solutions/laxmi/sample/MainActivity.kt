package solutions.laxmi.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import solutions.laxmi.lsnoti.LsSnackbarData
import solutions.laxmi.lsnoti.LsSnackbarHost
import solutions.laxmi.lsnoti.LsSnackbarType
import solutions.laxmi.lsnoti.LsSnacksPosition
import solutions.laxmi.lsnoti.rememberLsSnackbarHostState
import solutions.laxmi.sample.ui.theme.LsnotiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LsnotiTheme {
                val snackbarState = rememberLsSnackbarHostState()
                val count= remember { mutableIntStateOf(1) }
                Scaffold(
                    snackbarHost = {
                        LsSnackbarHost(
                            state = snackbarState,
                            maxStackHeightFraction = 0.7f,
                            maxStackWidthFraction = 0.7f,
                            position = LsSnacksPosition.TopCenter
                        )
                    }
                ) {i->
                    Column(modifier = Modifier.padding(i).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                        Button(onClick = {
                            snackbarState.show(
                                LsSnackbarData(
                                    message = "${count.value++} A Operation completed Operation completed",
                                    type = LsSnackbarType.Info,
                                    durationMillis = 30000
                                )
                            )
                        }) {
                            Text("Show Snackbar")
                        }
                        Button(onClick = {
                            snackbarState.show(
                                LsSnackbarData(
                                    message = "${count.value++} A Operation completed Operation completed",
                                    type = LsSnackbarType.Success,
                                    durationMillis = 30000
                                )
                            )
                        }) {
                            Text("Show Snackbar : Success")
                        }
                        Button(onClick = {
                            snackbarState.show(
                                LsSnackbarData(
                                    message = "${count.value++} A Operation completed Operation completed",
                                    type = LsSnackbarType.Warning,
                                    durationMillis = 30000
                                )
                            )
                        }) {
                            Text("Show Snackbar : Warning")
                        }
                        Button(onClick = {
                            snackbarState.show(
                                LsSnackbarData(
                                    message = "${count.value++} A Operation completed Operation completed",
                                    type = LsSnackbarType.Error,
                                    durationMillis = 30000
                                )
                            )
                        }) {
                            Text("Show Snackbar : Error")
                        }
                    }
                }
            }
        }
    }
}
