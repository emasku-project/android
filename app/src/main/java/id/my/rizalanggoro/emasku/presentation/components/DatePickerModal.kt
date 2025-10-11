package id.my.rizalanggoro.emasku.presentation.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    initialMillis: Long? = null,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialMillis,
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
            ) {
                Text("Selesai")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Batal")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}