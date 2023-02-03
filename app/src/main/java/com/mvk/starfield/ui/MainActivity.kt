package com.mvk.starfield.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mvk.starfield.ui.theme.StarFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarFieldTheme {
                StarField()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarFieldTheme {
        StarField()
    }
}