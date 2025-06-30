package solutions.laxmi.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import solutions.laxmi.lsnoti.LsSnackbarConfig
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

                Scaffold(
                    snackbarHost = {
                        LsSnackbarHost(
                            state = snackbarState,
                            maxStackHeightFraction = 0.7f,
                            position = LsSnacksPosition.Center
                        )
                    }
                ) {i->
                    Box(modifier = Modifier.padding(i)){
                        Button(onClick = {
                            snackbarState.show(
                                "Operation completed Operation completed",
                                LsSnackbarConfig(
                                    type = LsSnackbarType.Info,
                                )
                            )
                            snackbarState.show(
                                "Operation completed Operation completed",
                                LsSnackbarConfig(
                                    type = LsSnackbarType.Success,
                                )
                            )
                            snackbarState.show(
                                "Operation completed Operation completed",
                                LsSnackbarConfig(
                                    type = LsSnackbarType.Error,
                                )
                            )
                            snackbarState.show(
                                "Operation completed Operation completed",
                                LsSnackbarConfig(
                                    type = LsSnackbarType.Warning,
                                )
                            )
                        }) {
                            Text("Show Snackbar")
                        }
                    }
                }
            }
        }
    }
}
