package com.example.task4smartnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesScreen()
        }
    }
}

@Composable
fun NotesScreen() {

    var text by remember {
        mutableStateOf("")
    }

    var darkMode by remember {
        mutableStateOf(false)
    }

    val notes = remember {
        mutableStateListOf<Note>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Mode")

            Switch(
                checked = darkMode,
                onCheckedChange = {
                    darkMode = it
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text("Enter Note")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (text.isNotEmpty()) {
                    notes.add(Note(text))
                    text = ""
                }
            }
        ) {
            Text("Add Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(notes) { note ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(note.title)

                        Button(
                            onClick = {
                                notes.remove(note)
                            }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}