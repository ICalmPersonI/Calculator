import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.calmperson.calculator.MainViewModel
import com.calmperson.calculator.R
import com.calmperson.calculator.ui.theme.CalculatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { MyTopAppBar() }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            Display(modifier.weight(0.3f))
            Keyboard(modifier.weight(0.7f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 40.sp) },
        actions = {
            CalculationsHistory()
        }
    )
}

@Composable
fun CalculationsHistory(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    var expanded by remember { mutableStateOf(false) }
    val history = viewModel.calculationsHistory.observeAsState()

    IconButton(
        modifier = modifier.testTag("Calculations history"),
        onClick = { expanded = !expanded }
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.clipboard_text_clock_outline),
            contentDescription = null
        )
    }

    DropdownMenu(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        Column {
            history.value!!.forEach {
                Text(text = it, fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun Display(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    val text = viewModel.currExp.observeAsState()
    val textOverflow = viewModel.expOverFlow
    Box(modifier) {
        Text(
            modifier = Modifier
                .testTag("display")
                .padding(start = 10.dp, end = 10.dp)
                .drawBehind {
                    val strokeWidth = 5f
                    val x = size.width - strokeWidth
                    val y = size.height - strokeWidth + 10

                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, y),
                        end = Offset(x, y),
                        strokeWidth = strokeWidth
                    )
                }
                .align(Alignment.BottomEnd)
                .fillMaxWidth(),
            text = text.value!!,
            maxLines = 2,
            onTextLayout = {
                textOverflow.value = it.hasVisualOverflow
                println(it.hasVisualOverflow)
            },
            fontSize = 45.sp,
            textAlign = TextAlign.End,
            lineHeight = 50.sp
        )
    }
}

@Composable
fun Keyboard(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    val numbers = listOf(
        listOf(
            Pair(painterResource(R.drawable.numeric_7), '7'),
            Pair(painterResource(R.drawable.numeric_4), '4'),
            Pair(painterResource(R.drawable.numeric_1), '1'),
            Pair(painterResource(R.drawable.numeric_0), '0')
        ),
        listOf(
            Pair(painterResource(R.drawable.numeric_8), '8'),
            Pair(painterResource(R.drawable.numeric_5), '5'),
            Pair(painterResource(R.drawable.numeric_2), '2'),
            Pair(painterResource(R.drawable.circle_small), '.')
        ),
        listOf(
            Pair(painterResource(R.drawable.numeric_9), '9'),
            Pair(painterResource(R.drawable.numeric_6), '6'),
            Pair(painterResource(R.drawable.numeric_3), '3'),
            Pair(painterResource(R.drawable.equal), '='),
        )
    )
    val operators = listOf(
        Pair(painterResource(R.drawable.plus), '+'),
        Pair(painterResource(R.drawable.minus), '-'),
        Pair(painterResource(R.drawable.multiplication), '*'),
        Pair(painterResource(R.drawable.division), '/'),
        Pair(painterResource(R.drawable.exponent), '^')
    )

    Column(modifier.height(IntrinsicSize.Max)) {

        Row(Modifier.weight(0.2f)) {
            Button(
                modifier = Modifier
                    .testTag("clear")
                    .weight(1f)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                icon = painterResource(R.drawable.alpha_c),
                onClick = {
                    viewModel.clearExp()
                }
            )
            operators.drop(2).forEach { (icon, chr) ->
                Button(
                    modifier = Modifier
                        .testTag(chr.toString())
                        .weight(1f)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    icon = icon,
                    onClick = {
                        with(viewModel) {
                            addCharToExp(' ')
                            addCharToExp(chr)
                            addCharToExp(' ')
                        }
                    }
                )
            }
        }

        Row(Modifier.weight(0.8f)) {
            Row(modifier = Modifier
                .weight(0.75f)
                .fillMaxHeight()) {
                numbers.forEach {
                    Column(modifier = Modifier.weight(1f)) {
                        it.forEach { (icon, chr) ->
                            Button(
                                modifier = Modifier
                                    .testTag(chr.toString())
                                    .weight(1f)
                                    .fillMaxWidth(),
                                icon = icon,
                                onClick = {
                                    if (chr == '=')  {
                                        viewModel.calculate()
                                    } else {
                                        viewModel.addCharToExp(chr)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight()
            ) {
                Button(
                    modifier = Modifier
                        .testTag("backspace")
                        .heightIn(min = 60.dp)
                        .weight(1f)
                        .fillMaxWidth(),
                    icon = painterResource(R.drawable.backspace_outline),
                    onClick = {
                        viewModel.removeLast()
                    }
                )
                operators.take(2).forEach { (icon, chr) ->
                    Button(
                        modifier = Modifier
                            .testTag(chr.toString())
                            .weight(1f)
                            .fillMaxWidth(),
                        icon = icon,
                        onClick = {
                            with(viewModel) {
                                addCharToExp(' ')
                                addCharToExp(chr)
                                addCharToExp(' ')
                            }
                        }
                    )
                }
                Button(
                    modifier = Modifier
                        .testTag("plus_minus_variant")
                        .heightIn(min = 60.dp)
                        .weight(1f)
                        .fillMaxWidth(),
                    icon = painterResource(R.drawable.plus_minus_variant),
                    onClick = {
                        viewModel.addCharToExp('-')
                    }
                )
            }

        }
    }
}

@Composable
fun Button(modifier: Modifier = Modifier, icon: Painter, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayPreview() {
    CalculatorTheme {
        Display()
    }
}

@Preview(showBackground = true)
@Composable
fun KeyboardPreview() {
    CalculatorTheme {
        Keyboard()
    }
}

@Preview(showBackground = true, heightDp = 480, widthDp = 300)
@Composable
fun DefaultPreviewCompact() {
    CalculatorTheme {
        Calculator(modifier = Modifier)
    }
}

@Preview(showBackground = true, heightDp = 900, widthDp = 600)
@Composable
fun DefaultPreviewMedium() {
    CalculatorTheme {
        Calculator(modifier = Modifier)
    }
}

@Preview(showBackground = true, widthDp = 840, heightDp = 900)
@Composable
fun DefaultPreviewDesktop() {
    CalculatorTheme {
        Calculator(modifier = Modifier)
    }
}

@Preview(
    showBackground = true,
    widthDp = 840,
    heightDp = 900,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreviewDesktopNight() {
    CalculatorTheme {
        Calculator(modifier = Modifier)
    }
}