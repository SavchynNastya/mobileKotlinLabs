//package com.example.labs_mobile
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            FuelCalculatorScreen()
//        }
//    }
//}
//
//@Composable
//fun FuelCalculatorScreen() {
//    // Значення компонентів, що задаються за вашим варіантом
//    val hp = 2.8
//    val cp = 72.3
//    val sp = 2.0
//    val np = 1.1
//    val op = 1.3
//    val wp = 5.5
//    val ap = 15.0
//
//    var result by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Fuel composition:")
//
//        // Розрахунки
//        val fuel = FuelComponent(
//            hp = hp,
//            cp = cp,
//            sp = sp,
//            np = np,
//            op = op,
//            wp = wp,
//            ap = ap
//        )
//        val dryMass = calculateDryMass(fuel)
//        val combustibleMass = calculateCombustibleMass(fuel)
//        val heatingValue = calculateLowerHeatingValue(fuel)
//
//        result = """
//            Dry Mass: $dryMass%
//            Combustible Mass: $combustibleMass%
//            Lower Heating Value: $heatingValue MJ/kg
//        """.trimIndent()
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Виведення результату
//        Text(text = result)
//    }
//}
//
//// Логіка розрахунків
//fun calculateDryMass(fuel: FuelComponent): Double {
//    return 100 - fuel.wp
//}
//
//fun calculateCombustibleMass(fuel: FuelComponent): Double {
//    return 100 - fuel.ap - fuel.wp
//}
//
//fun calculateLowerHeatingValue(fuel: FuelComponent): Double {
//    return (33.9 * fuel.cp) + (142.5 * fuel.hp) - (12.6 * fuel.op)
//}
//
//data class FuelComponent(
//    val hp: Double,
//    val cp: Double,
//    val sp: Double,
//    val np: Double,
//    val op: Double,
//    val wp: Double,
//    val ap: Double
//)

//package com.example.labs_mobile
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.text.style.TextAlign
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            FuelCalculatorScreen()
//        }
//    }
//}
//
//@Composable
//fun FuelCalculatorScreen() {
//    var hp by remember { mutableStateOf("2.8") }
//    var cp by remember { mutableStateOf("72.3") }
//    var sp by remember { mutableStateOf("2.0") }
//    var np by remember { mutableStateOf("1.1") }
//    var op by remember { mutableStateOf("1.3") }
//    var wp by remember { mutableStateOf("5.5") }
//    var ap by remember { mutableStateOf("15.0") }
//
//    var result by remember { mutableStateOf("") }
//    var error by remember { mutableStateOf<String?>(null) }
//
//    Column(modifier = Modifier
//        .padding(16.dp)
//        .fillMaxSize()
//        .background(Color(0xFFEFEFEF))) {
//
//        Text("Fuel Composition Calculator", fontSize = 20.sp, color = Color.Blue, modifier = Modifier.padding(bottom = 16.dp))
//
//        FuelInputField(value = hp, onValueChange = { hp = it }, label = "HP")
//        FuelInputField(value = cp, onValueChange = { cp = it }, label = "CP")
//        FuelInputField(value = sp, onValueChange = { sp = it }, label = "SP")
//        FuelInputField(value = np, onValueChange = { np = it }, label = "NP")
//        FuelInputField(value = op, onValueChange = { op = it }, label = "OP")
//        FuelInputField(value = wp, onValueChange = { wp = it }, label = "WP")
//        FuelInputField(value = ap, onValueChange = { ap = it }, label = "AP")
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            result = calculateResults(hp, cp, sp, np, op, wp, ap)
//        }) {
//            Text("Calculate")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        if (error != null) {
//            Text(text = error!!, color = Color.Red, textAlign = TextAlign.Center)
//        } else {
//            Text(text = result)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text("Formulas Used:")
//        Text("Dry Mass = 100 - WP")
//        Text("Combustible Mass = 100 - AP - WP")
//        Text("Lower Heating Value = (33.9 * CP) + (142.5 * HP) - (12.6 * OP)")
//    }
//}
//
//@Composable
//fun FuelInputField(value: String, onValueChange: (String) -> Unit, label: String) {
//    var inputValue by remember { mutableStateOf(value) }
//
//    Column(modifier = Modifier.padding(bottom = 16.dp)) {
//        Text(text = label, fontSize = 16.sp)
//        BasicTextField(
//            value = inputValue,
//            onValueChange = {
//                inputValue = it
//                onValueChange(it)
//            },
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//            modifier = Modifier
//                .background(Color.White)
//                .fillMaxWidth()
//                .padding(8.dp)
//                .border(1.dp, Color.Gray)
//        )
//    }
//}
//
//// Calculate results and handle possible errors
//fun calculateResults(hp: String, cp: String, sp: String, np: String, op: String, wp: String, ap: String): String {
//    return try {
//        val hpVal = hp.toDouble()
//        val cpVal = cp.toDouble()
//        val spVal = sp.toDouble()
//        val npVal = np.toDouble()
//        val opVal = op.toDouble()
//        val wpVal = wp.toDouble()
//        val apVal = ap.toDouble()
//
//        // Perform calculations
//        val fuel = FuelComponent(
//            hp = hpVal,
//            cp = cpVal,
//            sp = spVal,
//            np = npVal,
//            op = opVal,
//            wp = wpVal,
//            ap = apVal
//        )
//        val dryMass = calculateDryMass(fuel)
//        val combustibleMass = calculateCombustibleMass(fuel)
//        val heatingValue = calculateLowerHeatingValue(fuel)
//
//        """
//            Dry Mass: ${"%.2f".format(dryMass)}%
//            Combustible Mass: ${"%.2f".format(combustibleMass)}%
//            Lower Heating Value: ${"%.2f".format(heatingValue)} MJ/kg
//        """.trimIndent()
//    } catch (e: Exception) {
//        "Error: ${e.message}"
//    }
//}
//
//fun calculateDryMass(fuel: FuelComponent): Double {
//    return 100 - fuel.wp
//}
//
//fun calculateCombustibleMass(fuel: FuelComponent): Double {
//    return 100 - fuel.ap - fuel.wp
//}
//
//fun calculateLowerHeatingValue(fuel: FuelComponent): Double {
//    return (33.9 * fuel.cp) + (142.5 * fuel.hp) - (12.6 * fuel.op)
//}
//
//data class FuelComponent(
//    val hp: Double,
//    val cp: Double,
//    val sp: Double,
//    val np: Double,
//    val op: Double,
//    val wp: Double,
//    val ap: Double
//)

//package com.example.labs_mobile
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.foundation.rememberScrollState
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            FuelCalculatorScreen()
//        }
//    }
//}
//
//@Composable
//fun FuelCalculatorScreen() {
//    var hp by remember { mutableStateOf("2.8") }
//    var cp by remember { mutableStateOf("72.3") }
//    var sp by remember { mutableStateOf("2.0") }
//    var np by remember { mutableStateOf("1.1") }
//    var op by remember { mutableStateOf("1.3") }
//    var wp by remember { mutableStateOf("5.5") }
//    var ap by remember { mutableStateOf("15.0") }
//
//    var result by remember { mutableStateOf("") }
//    var error by remember { mutableStateOf<String?>(null) }
//
//    val scrollState = rememberScrollState()
//
//    Box(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize()
//            .background(Color(0xFFF0F0F0))
//            .verticalScroll(scrollState) // Make the Column scrollable
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = 16.dp)
//        ) {
//            Text(
//                text = "Fuel Composition Calculator",
//                fontSize = 22.sp,
//                color = Color(0xFF007AFF),
//                modifier = Modifier.padding(bottom = 16.dp),
//                fontFamily = FontFamily.Serif
//            )
//
//            FuelInputField(value = hp, onValueChange = { hp = it }, label = "HP")
//            FuelInputField(value = cp, onValueChange = { cp = it }, label = "CP")
//            FuelInputField(value = sp, onValueChange = { sp = it }, label = "SP")
//            FuelInputField(value = np, onValueChange = { np = it }, label = "NP")
//            FuelInputField(value = op, onValueChange = { op = it }, label = "OP")
//            FuelInputField(value = wp, onValueChange = { wp = it }, label = "WP")
//            FuelInputField(value = ap, onValueChange = { ap = it }, label = "AP")
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    result = calculateResults(hp, cp, sp, np, op, wp, ap)
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
//            ) {
//                Text("Calculate", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (error != null) {
//                Text(
//                    text = error!!,
//                    color = Color.Red,
//                    textAlign = TextAlign.Center
//                )
//            } else {
//                Text(text = result)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text("Formulas Used:", fontSize = 18.sp, fontFamily = FontFamily.Serif)
//            Text("Dry Mass = 100 - WP", fontSize = 16.sp)
//            Text("Combustible Mass = 100 - AP - WP", fontSize = 16.sp)
//            Text("Lower Heating Value = (33.9 * CP) + (142.5 * HP) - (12.6 * OP)", fontSize = 16.sp)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FuelInputField(value: String, onValueChange: (String) -> Unit, label: String) {
//    var inputValue by remember { mutableStateOf(value) }
//
//    Column(modifier = Modifier.padding(bottom = 16.dp)) {
//        Text(text = label, fontSize = 16.sp, fontFamily = FontFamily.Serif)
//        TextField(
//            value = inputValue,
//            onValueChange = {
//                inputValue = it
//                onValueChange(it)
//            },
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//            modifier = Modifier
//                .background(Color.White)
//                .fillMaxWidth()
//                .padding(8.dp)
//                .border(1.dp, Color.Gray)
//        )
//    }
//}
//
//// Calculate results and handle possible errors
//fun calculateResults(hp: String, cp: String, sp: String, np: String, op: String, wp: String, ap: String): String {
//    return try {
//        val hpVal = hp.toDouble()
//        val cpVal = cp.toDouble()
//        val spVal = sp.toDouble()
//        val npVal = np.toDouble()
//        val opVal = op.toDouble()
//        val wpVal = wp.toDouble()
//        val apVal = ap.toDouble()
//
//        // Perform calculations
//        val fuel = FuelComponent(
//            hp = hpVal,
//            cp = cpVal,
//            sp = spVal,
//            np = npVal,
//            op = opVal,
//            wp = wpVal,
//            ap = apVal
//        )
//        val dryMass = calculateDryMass(fuel)
//        val combustibleMass = calculateCombustibleMass(fuel)
//        val heatingValue = calculateLowerHeatingValue(fuel)
//
//        """
//            Dry Mass: ${"%.2f".format(dryMass)}%
//            Combustible Mass: ${"%.2f".format(combustibleMass)}%
//            Lower Heating Value: ${"%.2f".format(heatingValue)} MJ/kg
//        """.trimIndent()
//    } catch (e: Exception) {
//        "Error: ${e.message}"
//    }
//}
//
//fun calculateDryMass(fuel: FuelComponent): Double {
//    return 100 - fuel.wp
//}
//
//fun calculateCombustibleMass(fuel: FuelComponent): Double {
//    return 100 - fuel.ap - fuel.wp
//}
//
//fun calculateLowerHeatingValue(fuel: FuelComponent): Double {
//    return (33.9 * fuel.cp) + (142.5 * fuel.hp) - (12.6 * fuel.op)
//}
//
//data class FuelComponent(
//    val hp: Double,
//    val cp: Double,
//    val sp: Double,
//    val np: Double,
//    val op: Double,
//    val wp: Double,
//    val ap: Double
//)

// ----- SAVED LAB1 ------
//package com.example.labs_mobile
//import com.example.labs_mobile.labs.lab1.task1.utils.FuelCalculator
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.KeyboardType
////import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.text.input.ImeAction
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            FuelCalculatorScreen()
//        }
//    }
//}
//
//@Composable
//fun FuelCalculatorScreen() {
//    var hp by remember { mutableStateOf(2.8) }
//    var cp by remember { mutableStateOf(72.3) }
//    var sp by remember { mutableStateOf(2.0) }
//    var np by remember { mutableStateOf(1.1) }
//    var op by remember { mutableStateOf(1.3) }
//    var wp by remember { mutableStateOf(5.5) }
//    var ap by remember { mutableStateOf(15.0) }
//
////    var result by remember { mutableStateOf("") }
//    var dryMassResult by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
//    var combustibleMassResult by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
//    var lowerHeatingValueResult by remember { mutableStateOf(0.0) }
//
//    var error by remember { mutableStateOf<String?>(null) }
//
//    val scrollState = rememberScrollState()
//
//    Box(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize()
//            .background(Color(0xFFF9F9F9)) // Light gray background
//            .verticalScroll(scrollState)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = 16.dp)
//        ) {
//            Text(
//                text = "Fuel Composition Calculator",
//                fontSize = 24.sp,
//                color = Color(0xFF333333), // Dark gray text
//                fontFamily = FontFamily.Serif,
//                modifier = Modifier
//                    .padding(bottom = 16.dp)
//                    .fillMaxWidth(),
//                textAlign = TextAlign.Center
//            )
//
//            FuelInputField(value = hp, onValueChange = { hp = it }, label = "HP")
//            FuelInputField(value = cp, onValueChange = { cp = it }, label = "CP")
//            FuelInputField(value = sp, onValueChange = { sp = it }, label = "SP")
//            FuelInputField(value = np, onValueChange = { np = it }, label = "NP")
//            FuelInputField(value = op, onValueChange = { op = it }, label = "OP")
//            FuelInputField(value = wp, onValueChange = { wp = it }, label = "WP")
//            FuelInputField(value = ap, onValueChange = { ap = it }, label = "AP")
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
////                    result = calculateResults(hp, cp, sp, np, op, wp, ap)
//                    dryMassResult = FuelCalculator.calculateDryMass(hp, cp, sp, np, op, wp, ap)
//                    combustibleMassResult = FuelCalculator.calculateCombustibleMass(hp, cp, sp, np, op, wp, ap)
//                    lowerHeatingValueResult = FuelCalculator.calculateLowerHeatingValueWorking(hp, cp, sp, op, wp)
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)), // Blue button
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .border(1.dp, Color(0xFF007BFF), shape = MaterialTheme.shapes.medium)
//                    .clip(MaterialTheme.shapes.medium)
//                    .background(Color(0xFF007BFF))
//            ) {
//                Text("Calculate", color = Color.White, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (error != null) {
//                Text(
//                    text = error!!,
//                    color = Color.Red,
//                    textAlign = TextAlign.Center,
//                    fontFamily = FontFamily.Serif,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            } else {
////                Text(
////                    text = result,
////                    fontFamily = FontFamily.Serif,
////                    modifier = Modifier.fillMaxWidth()
////                )
//                Text(
//                    text = "Dry Mass = $dryMassResult",
//                    fontFamily = FontFamily.Serif,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Text(
//                    text = "Combustible Mass = $combustibleMassResult",
//                    fontFamily = FontFamily.Serif,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Text(
//                    text = "Lower Heating Value = $lowerHeatingValueResult",
//                    fontFamily = FontFamily.Serif,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text("Formulas Used:", fontSize = 18.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
//            Text("Dry Mass = 100 - WP", fontSize = 16.sp, fontFamily = FontFamily.Serif)
//            Text("Combustible Mass = 100 - AP - WP", fontSize = 16.sp, fontFamily = FontFamily.Serif)
//            Text("Lower Heating Value = (33.9 * CP) + (142.5 * HP) - (12.6 * OP)", fontSize = 16.sp, fontFamily = FontFamily.Serif)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FuelInputField(value: Double, onValueChange: (Double) -> Unit, label: String) {
//    var inputValue by remember { mutableStateOf(value.toString()) }
//
//    Column(modifier = Modifier.padding(bottom = 16.dp)) {
//        Text(
//            text = label,
//            fontSize = 16.sp,
//            fontFamily = FontFamily.Serif
//        )
//        TextField(
//            value = inputValue,
//            onValueChange = {
//                if (it.all { char -> char.isDigit() || char == '.' }) { // Only allow digits and dots
//                    inputValue = it
//                    it.toDoubleOrNull()?.let { it1 -> onValueChange(it1) }
//                }
//            },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(onDone = {
//                // Handle the 'Done' action if needed
//            }),
//            modifier = Modifier
//                .background(Color.White)
//                .fillMaxWidth()
//                .padding(8.dp)
//                .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.medium) // Border radius
//                .clip(MaterialTheme.shapes.medium) // Rounded corners
//        )
//    }
//}
// ---- SAVED LAB 1--------
//fun FuelInputField(value: String, onValueChange: (String) -> Unit, label: String) {
//    var inputValue by remember { mutableStateOf(value) }
//
//    Column(modifier = Modifier.padding(bottom = 16.dp)) {
//        Text(
//            text = label,
//            fontSize = 16.sp,
//            fontFamily = FontFamily.Serif
//        )
//        TextField(
//            value = inputValue,
//            onValueChange = {
//                if (it.all { char -> char.isDigit() || char == '.' }) { // Only allow digits and dots
//                    inputValue = it
//                    onValueChange(it)
//                }
//            },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(onDone = {
//                // Handle the 'Done' action if needed
//            }),
//            modifier = Modifier
//                .background(Color.White)
//                .fillMaxWidth()
//                .padding(8.dp)
//                .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.medium) // Border radius
//                .clip(MaterialTheme.shapes.medium) // Rounded corners
//        )
//    }
//}

//// Calculate results and handle possible errors
//fun calculateResults(hp: String, cp: String, sp: String, np: String, op: String, wp: String, ap: String): String {
//    return try {
//        val hpVal = hp.toDouble()
//        val cpVal = cp.toDouble()
//        val spVal = sp.toDouble()
//        val npVal = np.toDouble()
//        val opVal = op.toDouble()
//        val wpVal = wp.toDouble()
//        val apVal = ap.toDouble()
//
//        // Perform calculations
//        val fuel = FuelComponent(
//            hp = hpVal,
//            cp = cpVal,
//            sp = spVal,
//            np = npVal,
//            op = opVal,
//            wp = wpVal,
//            ap = apVal
//        )
//        val dryMass = calculateDryMass(fuel)
//        val combustibleMass = calculateCombustibleMass(fuel)
//        val heatingValue = calculateLowerHeatingValue(fuel)
//
//        """
//            Dry Mass: ${"%.2f".format(dryMass)}%
//            Combustible Mass: ${"%.2f".format(combustibleMass)}%
//            Lower Heating Value: ${"%.2f".format(heatingValue)} MJ/kg
//        """.trimIndent()
//    } catch (e: Exception) {
//        "Error: ${e.message}"
//    }
//}
//
//fun calculateDryMass(fuel: FuelComponent): Double {
//    return 100 - fuel.wp
//}
//
//fun calculateCombustibleMass(fuel: FuelComponent): Double {
//    return 100 - fuel.ap - fuel.wp
//}
//
//fun calculateLowerHeatingValue(fuel: FuelComponent): Double {
//    return (33.9 * fuel.cp) + (142.5 * fuel.hp) - (12.6 * fuel.op)
//}
//
//data class FuelComponent(
//    val hp: Double, // Hydrogen percentage
//    val cp: Double, // Carbon percentage
//    val sp: Double, // Sulfur percentage
//    val np: Double, // Nitrogen percentage
//    val op: Double, // Oxygen percentage
//    val wp: Double, // Water percentage
//    val ap: Double  // Ash percentage
//)

package com.example.labs_mobile
import com.example.labs_mobile.labs.lab1.task1.utils.FuelCalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labs_mobile.labs.lab1.task1.screens.Lab1Task1Screen
import com.example.labs_mobile.labs.lab1.task2.screens.Lab1Task2Screen

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MainActivityScreen()
//        }
//    }
//}
//
//
//@Composable
//fun MainActivityScreen() {
//
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavigation()
            }
        }
//        setContent {
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "lab_list") {
//                composable("lab_list") { LabListScreen(navController) }
//                composable("task_list/{labId}") { backStackEntry ->
//                    val labId = backStackEntry.arguments?.getString("labId") ?: ""
//                    val screen = NavigationMappings.getLabScreen(labId) ?: "LabNotFound"
//                    LabScreen(navController, screen)
//                }
//                composable("task_details/{taskId}") { backStackEntry ->
//                    val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
//                    val screen = NavigationMappings.getTaskScreen(taskId) ?: "TaskNotFound"
//                    TaskDetailsScreen(screen)
//                }
//                composable("LabNotFound") { LabNotFoundScreen() }
//                composable("TaskNotFound") { TaskNotFoundScreen() }
//            }
//        }
//        setContent {
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = "lab_list") {
//                composable("lab_list") { LabListScreen(navController) }
//                composable("task_list/{labId}") { backStackEntry ->
//                    TaskListScreen(
//                        navController,
//                        backStackEntry.arguments?.getString("labId") ?: ""
//                    )
//                }
//                composable("task_details/{taskId}") { backStackEntry ->
//                    TaskDetailsScreen(
//                        backStackEntry.arguments?.getString("taskId") ?: ""
//                    )
//                }
//            }
//        }
    }
}

data class Lab(val id: Int, val name: String)
data class Task(val id: String, val name: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lab_list") {
        composable("lab_list") {
            LabListScreen(navController)
        }
        composable("task_list/{labId}") { backStackEntry ->
            val labId = backStackEntry.arguments?.getString("labId") ?: ""
            val taskListRoute = NavigationMappings.labToTaskList[labId] ?: "lab_list" // Default route if labId is not found
            TaskListScreen(navController, labId, taskListRoute)
        }
        composable("task_details/{labId}/{taskId}") { backStackEntry ->
            val labId = backStackEntry.arguments?.getString("labId") ?: ""
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            TaskDetailsScreen(taskId, labId)
        }
    }
}

@Composable
fun LabListScreen(navController: NavController) {
    val labs = listOf(
        Lab(1, "Lab 1"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(labs) { lab ->
            Button(
                onClick = { navController.navigate("task_list/${lab.id}") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Task List for ${lab.name}")
            }
        }
    }
}

@Composable
fun TaskButton(taskId: String, taskName: String, labId: String, navController: NavController) {
    Button(
        onClick = { navController.navigate("task_details/$labId/$taskId") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = taskName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskListScreen(navController: NavController, labId: String, taskListRoute: String) {
    val tasks: List<Task> = remember(labId) {
        when (labId) {
            "1" -> listOf(
                Task("1", "Task 1"),
                Task("2", "Task 2")
            )
            else -> emptyList()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            TaskButton(
                taskId = task.id,
                taskName = task.name,
                labId = labId,
                navController = navController
            )
        }
    }
}

private val lab1TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { Lab1Task1Screen() },
    "2" to { Lab1Task2Screen() }
)


@Composable
fun TaskDetailsScreen(taskId: String, labId: String) {
    when (labId) {
        "1" -> lab1TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        else -> Text("Unknown Lab")
    }
}