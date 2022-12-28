//package com.example.testborrarbeta
//
//import android.graphics.RenderEffect
//import android.graphics.RuntimeShader
//import android.os.Build
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.EaseInOut
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.RepeatMode
//import androidx.compose.animation.core.animateFloat
//import androidx.compose.animation.core.infiniteRepeatable
//import androidx.compose.animation.core.rememberInfiniteTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.produceState
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.geometry.center
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RadialGradientShader
//import androidx.compose.ui.graphics.ShaderBrush
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.graphics.asComposeRenderEffect
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.graphics.vector.Group
//import androidx.compose.ui.graphics.vector.Path
//import androidx.compose.ui.graphics.vector.PathParser
//import androidx.compose.ui.graphics.vector.rememberVectorPainter
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.onSizeChanged
//import androidx.compose.ui.text.ExperimentalTextApi
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.testborrarbeta.ui.theme.TestBorrarBetaTheme
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.launch
//import org.intellij.lang.annotations.Language
//
//import com.example.testborrarbeta.ThisIsFinePaths.dog
//import com.example.testborrarbeta.ThisIsFinePaths.dogBack
//import com.example.testborrarbeta.ThisIsFinePaths.eyes
//import com.example.testborrarbeta.ThisIsFinePaths.teeths
//import com.example.testborrarbeta.ThisIsFinePaths.tongue
//import com.example.testborrarbeta.ThisIsFinePaths.hat
//import com.example.testborrarbeta.ThisIsFinePaths.furniture
//import com.example.testborrarbeta.ThisIsFinePaths.furniture2
//import com.example.testborrarbeta.ThisIsFinePaths.cup
//import com.example.testborrarbeta.ThisIsFinePaths.smokeBack
//import com.example.testborrarbeta.ThisIsFinePaths.smoke
//import com.example.testborrarbeta.ThisIsFinePaths.fire0
//import com.example.testborrarbeta.ThisIsFinePaths.fireBack
//import com.example.testborrarbeta.ThisIsFinePaths.fire1
//import com.example.testborrarbeta.ThisIsFinePaths.fireBack2
//import com.example.testborrarbeta.ThisIsFinePaths.fire2
//import com.example.testborrarbeta.ThisIsFinePaths.fire3
//import com.example.testborrarbeta.ThisIsFinePaths.bubbleChat
//import com.example.testborrarbeta.ThisIsFinePaths.bubbleChat2
//
//import androidx.compose.ui.graphics.Shader
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Devices
//import com.example.testborrarbeta.PERLIN_NOISE
//import com.example.testborrarbeta.R
//import com.example.testborrarbeta.ThisIsFinePaths
//import com.example.testborrarbeta.largeRadialGradientGreen
//
///*
//* Copyright 2022 The Android Open Source Project
//*
//* Licensed under the Apache License, Version 2.0 (the "License");
//* you may not use this file except in compliance with the License.
//* You may obtain a copy of the License at
//*
//*     http://www.apache.org/licenses/LICENSE-2.0
//*
//* Unless required by applicable law or agreed to in writing, software
//* distributed under the License is distributed on an "AS IS" BASIS,
//* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//* See the License for the specific language governing permissions and
//* limitations under the License.
//*/
//
//@OptIn(ExperimentalTextApi::class)
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@Composable
//@Preview
//fun ThisIsFine() {
//    val vectorSmokeBack = rememberVectorPainter(
//        defaultWidth = 590.46f.dp,
//        defaultHeight = 653.1f.dp,
//        viewportWidth = 530.46f,
//        viewportHeight = 655.1f,
//        autoMirror = true,
//    ) { _, _ ->
//        val duration = 3000
//        val transition = rememberInfiniteTransition()
//        val translationY by transition.animateFloat(
//            initialValue = 0f,
//            targetValue = 20f,
//            animationSpec = infiniteRepeatable(
//                tween(duration, easing = EaseInOut),
//                repeatMode = RepeatMode.Reverse
//            )
//        )
//
//        Group(name = "smokeBack", translationY = 120f + translationY, translationX = 50f) {
//            Path(
//                ThisIsFinePaths.smokeBack,
//                fill = SolidColor(Color(0xFF18100C)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "smoke", translationY = 127f + translationY, translationX = 57f) {
//            Path(
//                ThisIsFinePaths.smoke,
//                fill = SolidColor(Color(0xFFA1735E)),
//                fillAlpha = 1f
//            )
//        }
//    }
//
//    val vectorFire = rememberVectorPainter(
//        defaultWidth = 590.46f.dp,
//        defaultHeight = 653.1f.dp,
//        viewportWidth = 530.46f,
//        viewportHeight = 655.1f,
//        autoMirror = true,
//    ) { _, _ ->
//        Group(name = "fire0", translationY = 260f, translationX = 60f) {
//            Path(
//                ThisIsFinePaths.fire0,
//                fill = SolidColor(Color(0xFFF5AD46)),
//                fillAlpha = 0.7f
//            )
//        }
//        Group(name = "fireBack", translationY = 280f, translationX = 50f) {
//            Path(
//                ThisIsFinePaths.fireBack,
//                fill = SolidColor(Color(0xFF18100C)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "fire1", translationY = 290f, translationX = 54f) {
//            Path(
//                ThisIsFinePaths.fire1,
//                fill = SolidColor(Color(0xFFF5AD46)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "fireBack2", translationY = 328f, translationX = 54f) {
//            Path(
//                ThisIsFinePaths.fireBack2,
//                fill = SolidColor(Color(0xFF18100C)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "fire2", translationY = 358f, translationX = 58f) {
//            Path(
//                ThisIsFinePaths.fire2,
//                fill = SolidColor(Color(0xFFFFCF4F)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "fire3", translationY = 500f, translationX = 70f) {
//            Path(
//                ThisIsFinePaths.fire3,
//                fill= SolidColor(Color(0xFFF5AD46)),
//                fillAlpha = 1f
//            )
//        }
//    }
//
//    val vectorFixed = rememberVectorPainter(
//        defaultWidth = 590.46f.dp,
//        defaultHeight = 653.1f.dp,
//        viewportWidth = 530.46f,
//        viewportHeight = 655.1f,
//        autoMirror = true,
//    ) { _, _ ->
//        Group(name = "bubbleChat", translationY = 85f, translationX = 150f) {
//            Path(
//                ThisIsFinePaths.bubbleChat,
//                fill = SolidColor(Color(0xFF18100C)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "bubbleChat2", translationY = 94f, translationX = 157f) {
//            Path(
//                ThisIsFinePaths.bubbleChat2,
//                fill = SolidColor(Color(0xFFFFFFFF)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dog", translationY = 260f, translationX = 54f) {
//            Path(
//                ThisIsFinePaths.dogBack,
//                fill = SolidColor(Color(0xFF18100C)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dog", translationY = 323f, translationX = 103f) {
//            Path(
//                ThisIsFinePaths.dog,
//                fill = SolidColor(Color(0xFFE59825)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "hat", translationY = 263f, translationX = 110f) {
//            Path(
//                ThisIsFinePaths.hat,
//                fill = SolidColor(Color(0xFFE59825)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dogeyes", translationY = 265f, translationX = 110f) {
//            Path(
//                ThisIsFinePaths.eyes,
//                fill = SolidColor(Color(0xFFFFFFFF)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dogteeths", translationY = 261f, translationX = 111f) {
//            Path(
//                ThisIsFinePaths.teeths,
//                fill = SolidColor(Color(0xFFFFFFFF)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dogtongue", translationY = 265f, translationX = 112f) {
//            Path(
//                ThisIsFinePaths.tongue,
//                fill = SolidColor(Color(0xFFE46948)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dogfurniture", translationY = 485f, translationX = 59f) {
//            Path(
//                ThisIsFinePaths.furniture,
//                fill = SolidColor(Color(0xFFC98825)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "dogfurniture2", translationY = 557f, translationX = 361f) {
//            Path(
//                ThisIsFinePaths.furniture2,
//                fill = SolidColor(Color(0xFFC98825)),
//                fillAlpha = 1f
//            )
//        }
//        Group(name = "cup", translationY = 495f, translationX = 412f) {
//            Path(
//                ThisIsFinePaths.cup,
//                fill = SolidColor(Color(0xFFEECF9E)),
//                fillAlpha = 1f
//            )
//        }
//    }
//
//    val time by produceState(55f) {
//        while (true) {
//            withInfiniteAnimationFrameMillis {
//                value = it.toFloat() / 200f
//            }
//        }
//    }
//    val shader = RuntimeShader(PERLIN_NOISE)
//
//    Image(
//        vectorFire, contentDescription = "fire",
//        modifier = Modifier
//            .fillMaxSize()
//            .background(largeRadialGradientGreen)
//            .onSizeChanged { size ->
//                shader.setFloatUniform(
//                    "resolution",
//                    size.width.toFloat(),
//                    size.height.toFloat()
//                )
//            }
//            .graphicsLayer {
//                shader.setFloatUniform("time", time)
//                renderEffect = RenderEffect
//                    .createRuntimeShaderEffect(
//                        shader,
//                        "contents"
//                    )
//                    .asComposeRenderEffect()
//            }
//    )
//    Image(
//        vectorSmokeBack, contentDescription = "smoke",
//        modifier = Modifier
//            .fillMaxSize()
//
//    )
//    Image(
//        vectorFixed, contentDescription = "dog and bubble",
//        modifier = Modifier
//            .fillMaxSize()
//    )
//
//    Column() {
//        Text(
//            "THIS IS FINE.",
//            modifier = Modifier.padding(top = 250.dp)
//                .padding(start = 165.dp),
//            style = TextStyle(
//                brush = SolidColor(Color(0xFF18100C)),
//                fontWeight = FontWeight.ExtraBold, fontSize = 25.sp,
//                fontFamily = FontFamily(Font(R.font.unkempt)),
//                textAlign = TextAlign.Center
//            )
//        )
//    }
//}
//
//
//object ThisIsFinePaths {
//    val dog = PathParser().parsePathString(
//        "M238.83,116.38h0c-7.85,4.33-15.69,8.46-24.93,9.28-10.06,.89-21.12-4.49-24.26-12.87-3.1-8.25-3.2-16.6,1.4-24.45,2.08-3.56,4.71-6.8,7.09-10.18,.48-.68,1.08-1.47,.37-2.19-.38-.39-1.33-.62-1.84-.42-6.92,2.63-14.62,2.48-21.19,6.6-5.35,3.36-10.01,7.14-14.4,11.79-12.81,13.55-32.63,7.83-44.44-1.79-2.42-1.97-3.11-2.5-4.05,.8-2.98,10.52-9.21,18.57-18.68,24.1-3.99,.75-7.84,2.27-11.98,2.16h0s0,0,0,0c-.63-.16-.91,.19-1.05,.73-8.56,.63-16.71-.89-23.76-5.84-14.86-10.45-20.52-29.87-14.48-47.89,3.98-11.87,8.42-23.14,22.19-27.12,3.72-1.08,7.61-.67,11.2-2.05,14.18,1.03,26.1,6.21,33.71,19.07,1.67-1.73,2.29-3.18,2.15-4.93,.97-1.73,1.94-3.46,2.92-5.19,3.16-7.8,8.28-13.92,15.55-18.21,0,0,0,0,0,0,0,0,0,0,0,0,3.9,.97,6.52-2.75,10.33-2.09,.11-.4,.23-.8,.34-1.2-1.37-1.14-2.74-2.28-4.11-3.42,0,0,0,0,0,0,0,0,0,0,0,0-2.29-4.62-6.26-7.68-10.11-10.84h0s0,0,0,0c-1.64-1.27-3.28-2.54-4.92-3.81-1.33-1.18-2.47-2.81-4.03-3.45-3.07-1.27-6.46-5.28-9.6-1.16-2.17,2.85-4.82,4.78-7.46,6.92h0s0,0,0,0c-.98-.03-1.32,.73-1.74,1.4-2.4,1.12-4.79,2.24-7.19,3.36h0c-3.29,2.04-7.16,2.49-10.71,3.82-3.03,.78-6.36,.1-9.2,1.84-1.6,.39-3.19,.78-4.79,1.16-7.13,.67-14.26,.72-21.4-.01-.82,0-1.66,.13-2.46-.01-3.03-.53-5.93-3.19-8.81-2.23-3.37,1.12-1.53,5.54-3.27,8.01-.09,.13-.1,.31-.16,.47-1.66,4.14-3.32,8.27-4.98,12.41-.37,4.57,.45,8.96,1.97,13.24h0s0,0,0,0c.64,5.39,1.27,10.78,1.91,16.17,.16,4.98-.79,10.12,1.98,14.75h0c.28,.18,.32,.41,.14,.69-.44,6.84,.01,13.61,1.76,20.26h0c1.23,5.8-1.11,11.34-1.21,17.06-.1,6.16-3.52,11.73-3.49,17.96,0,0,0,0,0,0s0,0,0,0c-2.27,2.22-2.58,5.46-4.2,8.02-3.46,4.32-6.5,8.99-11.28,12.12-1.72,1.13-2.45,3.13-2.56,5.11-.2,3.64-1.39,7.03-2.17,10.53h0c-1.6,5.04-3.2,10.08-4.8,15.12h0c-6.07,22.08-4.76,44.2-.37,66.35,.3,1.49,.66,2.56,2.37,2.56,7.38,0,14.76,0,22.92,0-7.39-16.46-9.02-32.97-7.82-49.88,.2-2.75-.96-5.62,.75-8.24,.25-.76,.43-1.52,.52-2.27,1.21-6.21,.85-12.71,3.45-18.66,2.49,1.68,.71,4.02,.93,6.05,0,0,.2,.14,.2,.14v.05c-.07-.06-.13-.13-.2-.19-1.56,9.98-.58,20.04-.79,30.06-.11,5.39-.32,10.86,1.76,16.04,0,0,0,0,0,0-.07,5.02,1.26,9.75,2.82,14.47,4.31,13.08,4.26,13.01,17.85,12.5,3.29-.12,3.56-1,2.3-3.78-5.09-11.24-7.81-23.11-9.06-35.34,.56-12.94-.88-25.95,1.17-38.84,.49-1.07-.17-2.71,1.78-3.25,1.11,2.42,.97,4.93,.77,7.48-.09,1.17,.2,2.04,1.52,.83,.93,3.3-2.17,5.71-1.44,9.46,1.31,6.73,.93,13.77,1.34,20.68,.13,2.26-.38,4.65,1.04,6.71,.63,10.84,3.89,20.99,7.69,31.08,1.4,3.71,3.02,5.09,7.09,5.06,25.95-.21,51.91-.12,77.86-.15q13.12-.02,17.4-11.83h0s0,0,0,0c.97-2.98,1.51-6.04,1.69-9.17h0c2.03-16.27-2.7-31.45-7.51-46.62-.61-1.91-1.54-2-3.36-1.28-4.94,1.96-10.07,3.3-15.45,3.15-1.23-.03-3.12,.56-3.38-1.17-.36-2.41,1.9-1.58,3.17-1.92,1.91-.52,3.91-.81,5.73-1.56,5.1-2.11,11.22-1.81,15.27-6.41,2.92-2.53,5.8-5.09,6.68-9.11,.79-4.75,.99-9.54,.97-14.35,0-1.34,.05-2.82-1.75-3.06-1.88-.24-3,.97-2.87,2.61,.26,3.49-1.65,6.33-2.42,9.49h0c-6.29,10.45-15.52,13.84-27.21,10.34-14.51-4.34-25.41-13.25-33.63-25.73-1.36-2.06-2.46-4.96-4.4-5.81-2.29-1-4.02,2.23-6.19,3.26-.69,.33-1.59,.23-2.39,.32h0s0,0,0,0c.37-2.05,1.39-3.71,3.03-4.99,5.83-3.84,10.96-8.68,17.2-11.94h0c1.64-.95,3.27-1.89,4.91-2.84,.67-.38,1.34-.76,2-1.14,0,0,0,0,0,0,2.85-.89,5.66-2.51,8.85-.84-.79,2.52-4.12,1.75-4.6,4.22,10.86,7.93,23.78,10.54,36.16,14.5,5.39,1.73,10.9,3,16.55,2.68,13.55-.76,27.24-.5,40.47-4.04,8.07-2.16,16.48-3.57,23.57-8.51,12.2-5.84,17.89-16.21,20.9-30.06-4.35,3.62-7.73,6.44-11.12,9.26Zm-186.15,4.84c-.12-.18-.23-.33-.34-.46,.06,.02,.11,.04,.18,.07,.08,.13,.13,.26,.16,.39Zm28.84,45.09c-.33,.08-.58,.02-.78-.14,.24,.02,.5,.06,.78,.14Z"
//    ).toNodes()
//    val dogBack = PathParser().parsePathString(
//        "M421.86,290.41c.48-4.4,.77-9.73,.92-15.42,.02,2.38,.06,3.89,.12,3.89,24-.04,21.74-4.75,22.14-20.21,.4-15.46-14.58-17.88-22.14-16.51,0,0-.06,6.37-.1,13.8-.22-10.42-.77-19.92-1.43-25.08-.8-6.19-70.08,4.34-70.08,4.34l.09,3.93h-.04s.03,.01,.04,.02l1.36,56.87s.54,.66,2.08,1.6c-35.01,7.67-54.79,16.47-58.85,28.52h-87.21c5.22-9.22,15.58-34.91-3.2-71.87,0,0,16.98-4.99,13.64-28.64,0,0,78.81,2.49,85.66-51.12,0,0,26.53-29.85-4.13-47.88-7.87-4.63-34.1-3.35-47.24,10.99l-30.45,8.36c22.46-41.75-24.9-53.33-24.9-53.33-6.52-14.95-21.09-27.44-31.65-29.93,0,0,11.07-40.59-24.64-31.11,0,0-23.94-44.59-56.42-27.84-31.83,16.41-11.82,51.84-11.82,51.84-26.56,6.47-30.75,34.07,.27,42.7,6.52,1.81-69.27,55.69-51.99,107.26,2.86,8.53,6.12,14.44,9.61,18.54-.61,.47-1.21,.95-1.8,1.45-2.04,9.55-3.61,19.26-4.83,28.72,1.21-10.55,2.78-20.58,4.83-28.72,.47-.46,.96-.87,1.46-1.21,0,0-16.37-5.06-31.18,5.88l.61,96.85,20.29-.08,29.16,.08c.24,.58,.48,1.16,.73,1.74l155.54,1.09h237.88v-39.57s-8.99-.28-22.36,.08Z"
//    ).toNodes()
//    val eyes = PathParser().parsePathString(
//        "M149.97,97.94c5.58,2.25,9.48,6.72,11.74,12.16-1.58-9.62-6.81-16.43-17.96-19.45-20.54-5.57-32.09,7.53-37.04,26.1-.43,1.63-.77,3.27-1.01,4.88l-5.12,1.17c-5.26-12.24-18.13-19.4-32.46-19.46-23.15-.09-31.88,17.01-31.88,37.46s14.61,34.7,32.63,34.7,33.48-12.49,34.21-39.01c.14-5.04-.79-9.68-2.51-13.7l5.12-1.17c3.69,6.09,5.12,13.53,3.26,21.64,3.62,6.61,9.82,11.25,18.33,12.73,12.85,2.24,23.63-1.08,29.69-12.88-6.32,6.75-16.52,9.91-24.72,6.6-10.72-4.33-13.39-20.23-9.18-32.25,5.27-15.04,15.37-24.17,26.9-19.51Zm-71.81,72.86c-12.08,.75-23.23-13.1-23.23-26.54s6.24-27.39,19.23-28.21c12.5-.79,25.51,13.07,25.51,29.17,0,17.44-9.68,24.85-21.5,25.58Z"
//    ).toNodes()
//    val teeths = PathParser().parsePathString(
//        "M140.13,214.05c-.74-.22-2.35-.69-4.23-1.22-1.1-.31-2.1-.59-2.9-.81-.55-.42-1.34-.59-2.05-.74-3.55-.73-7-1.74-10.33-3.18-.18-.08-.92-.31-1.08-.37-.62-.26-1.75-.65-3.72-1.4-1.72-.66-3.17-1.32-3.94-1.6-.99-.68-2.6-.93-3.66-1.41-3.22-1.45-6.05-3.39-8.39-5.99-1.64-1.82-3.07-2.77-5.35-.79-2.27,1.98-2.19,3.88-.46,5.98,3.29,3.99,7.33,6.6,13.21,6.72,1.93,.3,4.87-.04,5.45-2.34,.24-.97-.23-1.73-.8-2.18,.81,.32,2.37,.99,3.94,1.6,1.54,.59,2.89,1.1,3.72,1.4-1.92-.44-2.42,.84-2.3,3.04,.2,3.76,3.51,6.28,8.74,6.38,1.86,.39,3.57-.69,5.39-1.45,1.1-.46,2.33-.85,2.34-2.19,0-.64-.36-1.19-.7-1.5,.77,.22,1.88,.52,2.9,.81,1.68,.48,3.4,1,4.23,1.22-.51-.06-1.3-.14-1.79-.18-1.79-.11-1.67,1.74-1.49,3.05,.27,2.01,1.34,3.71,3.52,3.2,1.88-.44,3.35-1.96,4.32-3.9-1.29-1.41-2.91-1.94-4.55-2.18Z"
//    ).toNodes()
//    val hat = PathParser().parsePathString(
//        "M98.07,29.07c-2.61-2.72-5.79-1.77-8.88-1.35-6.28,3.35-10.49,8.91-15.07,14.08,0,0,0,0,0,0,0,0,0,0,0,0-3.79,3.33-7.39,6.93-11.43,9.93-6.32,4.68-12.69,8.41-21.54,6.36-5.86-1.35-11.54-3.42-17.49-4.49l-2.66-7.29c5.4-2.67,10.74-5.47,16.24-7.92,10.8-4.82,22.39-7.21,33.68-10.51,4.09-.41,5.59-2.02,2.77-5.78-.55-.73-.83-1.67-1.24-2.51,0,0,0,0,0,0C67.61,7.73,53.68-.61,39.81,.04c-8.9,.42-19.1,9.19-20.71,17.81-1.8,3.01-.82,6.56-1.87,9.71-1.25,3.02-1.05,6.02,.09,9.01,1.29,3.27,.26,7.24,2.98,10.09l3.21,6.91c-.47-.08-.94-.17-1.41-.24-7.05-1.04-13.36-.38-18.33,5.41C-.03,63.18-.73,68.43,.67,73.7c1.51,5.68,6.69,8.26,11.43,10.81,6.72,3.62,7.73,3.13,10.63-3.84,.3-.73,.86-1.35,1.3-2.02,1.35-1.72,.74-2.98-.96-3.93-1.56-.87-3.25-1.45-4.87-2.22,3.09,1.45,6.31,2.31,9.73,2.25,4.92,2.04,10.19,1.5,15.31,2.08,4.99,.58,9.98,.71,14.96-.09,1.26-.36,2.52-.73,3.78-1.09,3.66-1.05,7.75,.42,11.2-1.89,1.26-.65,2.51-1.3,3.77-1.95,7.79-1.65,14.82-5.22,20.18-10.85,7.66-8.05,12.63-21.74,.94-31.89Z"
//    ).toNodes()
//    val tongue = PathParser().parsePathString(
//        "M112.66,226.7c.06,5.34,0,5.38-4.59,2.86-10.13-5.56-17.24-14.25-24.09-23.23-2.42-3.17,.94-3.44,2.28-4.64,1.5-1.34,2.88-2.72,4.57,.24,3.43,6,8.8,9.32,15.74,9.85,1.75,.13,2.65,.81,3.2,2.47,1.34,4.07,3.06,8.03,2.89,12.45Z"
//    ).toNodes()
//    val furniture = PathParser().parsePathString(
//        "M23.67,8.11c.37-.39-.81-.74-.41-1.03,0,0-11.68-4.65-23.25,4.73L.48,94.78l15.03-.07S17.59,5.99,23.21,7.81c-.68,6.07-3.8,86.97-3.8,86.97h20.7c-9.41-45.85,3.51-83.11,5.99-88.69,3.92-8.85-11.96-7.97-22.44,2.02Z"
//    ).toNodes()
//    val furniture2 = PathParser().parsePathString(
//        "M131.36,27H0C.85,17.33,31.73,7.98,75.54,8.95c61.55,1.36,19.01-8.95,55.81-8.95V27Z"
//    ).toNodes()
//    val cup = PathParser().parsePathString(
//        "M63.66,11.33l-.06,8.48c5.42,.97,11.38,7.17-.04,12,0,0,.01,4.5,.04,5.74l-4.92-.25c.21-13.66-.39-28.87-1.21-35.6C56.8-3.7,0,5.48,0,5.48l.03,1.16,57.31,.56c.28,11.4-46.78,20.23-57.34,4.51L1.19,58.53s8.1,10.56,53.69,5.13c2.44-.29,3.51-11.77,3.77-25.04h4.98c0,.1,.02,.16,.03,.16,17.95-.03,16.26-3.55,16.56-15.11,.3-11.56-10.9-13.37-16.56-12.34Z"
//    ).toNodes()
//
//    val smokeBack = PathParser().parsePathString(
//        "M459.18,88.93V35.72h0V.14L1.34,0l-.26,23.29m0,.19L0,119.04s46.65,19.21,79.5-3.83c5.87-4.12,30.33,37.28,103.16,10.16l7.23-6.05c71.17,42.84,124.93,8.21,124.93,8.21,60.28,42.58,87.19,10.95,104.16,3.28,17.64,6.07,40.2,4.55,40.2,4.55v-46.16m-2.05,23.13c.64,0,1.19,.01,1.62,.06l-1.62-.06"
//    ).toNodes()
//    val smoke = PathParser().parsePathString(
//        "M445.85,77.67V44.03c-1.51,.27-14.92,.67-17.77-2.61-2.72-3.13,4.77,1.14,17.77,2.53v-12.75l-8.95,.49c.55-1.94,8.18-2.06,8.95-.9V.12L1.3,0l-.15,22.31c24.4,8.15,61.06-7.57,61.06-7.57-1.98,5.94-42.57,17.6-61.22,10.12L0,103.97s45.29,16.78,77.19-3.35c5.7-3.6,29.45,32.56,100.16,8.88l7.02-5.28c69.1,37.42,121.3,7.17,121.3,7.17,58.53,37.19,84.66,9.57,101.14,2.86,17.12,5.3,39.04,3.97,39.04,3.97v-21.75l-1.99,1.64c-4.1,3.66-15.77,2.85-28.36-1l26.91-1.75c-.42,.17,3.07,1.08,3.44,1.12v-14.88c-12.2-.59-54.73-6.89-49.19-12.19,0,0,16.28,8.19,49.19,8.26ZM38.75,51.04c21.97,1.88,25.45,3.69,51.8-3.62s10.08-1.11-2.85,2.64c-12.93,3.75-28.3,8.27-49.23,3.82s.28-2.85,.28-2.85Zm45.71,40.23c-7.42-3.14,.1-2.01,.1-2.01,7.79,1.32,9.03,2.6,18.37-2.55,9.35-5.15,3.58-.78-1.01,1.86-4.59,2.65-10.04,5.84-17.46,2.7Zm22.88,1.76c-2.04,5.3-6.73,4.72-10.65,4.26s-9.45-1.88-1.61-1.14c7.85,.74,7.75-.76,11.04-4.72,0,0,3.26-3.7,1.22,1.6Zm-.29-26.27c-2.83,1.9-7.76,4.81-12.26,7.18-11.24,5.93-24.6,13.05-44.14,9.36-19.54-3.69-.11-3.62-.11-3.62,20.16,.35,23.54,2.31,46.5-9.33,1.72-.87,3.2-1.61,4.52-2.26-4.24,.94-9.52,1.97-14.41,2.77-17.15,2.8-37.53,6.22-63.04-4.05-25.51-10.27,.96-3.96,.96-3.96,27.37,6.85,31.38,10.06,66.24,4.83,7.68-1.15,12.6-1.78,15.43-2.02,2.85-1.23,3.19-1.07,2.01-.12,2.07-.04,1.05,.47-1.71,1.22Z"
//    ).toNodes()
//
//
//    val fire0 = PathParser().parsePathString(
//        "M368.84,7.82c3.35,30-35.01,95.78-35.01,95.78,0,0-7.75-5.11-14.17-2.35-7.57-18.82-30.19-73.39-27.07-45.43,3.35,30-35.01,95.78-35.01,95.78,0,0-24.53-61.66-20.63-15.92,3.35,39.41-12.38,77.2-21.89,95.96-11.69-16.51-19.99-34.59-19.07-52.05,2.74-51.88-19.59,63.07-19.59,63.07,0,0-39.7-49.03-55.62-74.75-15.55-25.13,1.58,54.23,2.38,57.9-3.12-4.67-63.09-95.45-37.17-141.97,25.37-45.53-45.47,48.35-45.47,48.35,0,0-14.1-61.2-17.07-91.2C20.47,10.98,0,94.27,0,94.27L123.18,225.88s.02,.07,.02,.07l.09,.04,.02,.02s0-.01-.01-.02l156.43,68.45s-38.26-26.71-63.08-60.6l94.68-113.63c-.16,3.4-.07,7.34,.32,11.92,1.22,14.3,.02,25.45-2.43,34.14l89.11-106.96s-33.22-84.99-29.48-51.49Z"
//    ).toNodes()
//    val fireBack = PathParser().parsePathString(
//        "M2.38,308.12L0,60.83S12.19,31.84,24.54,26.73s-17.84,9.38,2.45,61.47c11.65,29.91-5.35-12.74,19.33-43.15C80.85,2.49,25.39,54.54,63.14,125.46c37.75,70.92-7.13-3.6,20.43-44.73,24.91-37.18-21.77,21.26,51.14,107.37,58.63,69.24-.73-55.51,9.92-46.06,17.64,15.64,30.66,29.79,33.83,45.72,3.18,15.93,16.93-5.44,13.95-16.93-2.98-11.48,27.09,45.95,33.88,77.36s7.65-77.69,50.24-124.28c42.58-46.58,48.57-54.33,49.79-74.51,1.22-20.18,6.49,26.81,8.67,60.54s-6.38-5.43,29.43-34.43c35.81-29,48.51-39.36,52.82-62.48,4.51-24.18,5.11-15.22,22.31,31.39,16.31,44.2,4.72,258.7,4.72,258.7L2.38,308.12Z"
//    ).toNodes()
//    val fire1 = PathParser().parsePathString(
//        "M2.34,291.13L0,57.47S11.97,30.09,24.11,25.25c12.13-4.83-17.52,8.87,2.41,58.08,11.45,28.26-5.26-12.04,18.98-40.77C79.42,2.36,24.94,51.53,62.02,118.54c37.08,67.01-7-3.4,20.07-42.27,24.47-35.13-21.38,20.09,50.24,101.45,57.58,65.42-.72-52.45,9.75-43.52,17.32,14.78,30.11,28.15,33.23,43.2,3.12,15.05,16.63-5.14,13.7-16-2.93-10.85,26.61,43.42,33.28,73.1,6.67,29.68,7.52-73.41,49.34-117.43,41.83-44.01,47.7-51.33,48.9-70.4s6.38,25.33,8.52,57.2c2.14,31.87-6.26-5.13,28.9-32.53,35.17-27.4,47.65-37.19,51.88-59.04,4.43-22.84,5.02-14.38,21.92,29.66,16.02,41.76,4.63,244.44,4.63,244.44L2.34,291.13Z"
//    ).toNodes()
//    val fireBack2 = PathParser().parsePathString(
//        "M1.54,64.17s4.82-23.14,9.55-31.28c4.73-8.14,4.75,28.85,18.76,52.04,0,0,.73-12.85,13.02-43.72,.98-2.46,6.8,23.94,14.99,53.87,0,0,16.69,39,62.82,91.52,46.13,52.52,28.3,0,28.3,0,0,0,16.88,11.85,21.42,37.16s42.25-28.88,39.74-28.88,22.42,33.16,29.94,36.79,35.97-108.69,64.68-152.53c28.7-43.84,6.61-4.08,32.06,28.2,13.51,17.14,18.81-34.23,63.17-83.15,0,0-6.74,41.57-5.69,48.93s34.79-45.54,32.19-68.01c-2.6-22.46,10.88,36.13,17.94,40.55s0,212.53,0,212.53L0,258.88,1.54,64.17Z"
//    ).toNodes()
//    val fire2 = PathParser().parsePathString(
//        "M434.37,39.74c-6.9-3.85-20.07-54.84-17.53-35.29,2.54,19.55-30.44,65.6-31.47,59.19-1.02-6.41,5.56-42.58,5.56-42.58-43.35,42.58-48.53,87.29-61.74,72.37-24.87-28.1-3.28-62.7-31.34-24.55-28.05,38.15-55.86,135.91-63.21,132.75-6.7-2.88-27.52-27.09-29.31-31.37-2.48,5.58-34.67,44.98-38.8,24.48-4.44-22.03-20.94-32.34-20.94-32.34,0,0,17.42,45.71-27.66,0-45.08-45.71-61.4-79.65-61.4-79.65-8-26.04-13.69-49.02-14.65-46.88-12.01,26.87-12.72,38.05-12.72,38.05C15.48,53.74,15.46,21.55,10.83,28.63,6.21,35.71,1.5,55.85,1.5,55.85L0,225.32l434.37-.6s6.9-181.13,0-184.98Zm-125.95,102.2h1.44l-10.23,40.21h-1.68l10.47-40.21Zm-3.45,53.18h-.25l-11.12,11.13-1.07-.37,11-10.82,9.04-21.95h1.25l-8.86,22.01Zm87.9-80.62l11.45-25.96h1.58l-11.19,25.96h-1.84Zm27.96-22.21l-.05,.09-.35,1.05-.22,.03-13.26,24.8h-2.29l13.62-24.88,9.34-26.99,1.94-.22-8.75,26.12h.02Z"
//    ).toNodes()
//    val fire3 = PathParser().parsePathString(
//        "M325.18,82.69s-22.04-52.87-24.6-37.74c-2.56,15.13-31.59,31.98-31.59,31.98,0,0,28.05-42.36,3.03-17.73-17.47,17.2-48.58,22.52-65.78,24.17-44.7-2.8-120.43-12.8-141.51-48.16-30.47-51.12,3.69,36.78,3.69,36.78,0,0-35.36-34.96-38.47-66.35C26.84-25.75,0,83.95,0,83.95l234.33,.49c-4.27-.04-10.78-.18-18.84-.55l109.68-1.19Z"
//    ).toNodes()
//
//    val bubbleChat = PathParser().parsePathString(
//        "M125.64,232.8c.22-4.96,1.94-10.11,3.14-14.7,3.2-12.24,5.96-24.72,7.97-37.13,1.64-10.1,3.06-19.45,3.14-29.72,.05-6.79-3.65-8.97-9.91-8.78-19.89,.59-33.86-.18-53.57-2.55-19.41-2.33-38.4-7.23-54.18-20.58C1.97,102.21-5.34,81.11,4.04,55.05c4.65-12.93,14.54-22.17,25.82-29.3C79.42-5.57,236.11-14.74,306.35,35.19c9.89,7.03,15.21,17.89,14.89,49.45-.91,6.54-2.87,12.77-5.09,18.98-5.41,15.17-16.58,23.87-30.21,29.05-15.67,5.94-31.68,10.43-48.56,11.76-22.59,1.77-38.35,3.26-61,3.14-4.59-.02-7.41,2.65-8.19,8.04-1.46,10.1-4.2,19.8-7.56,29.72-6.07,17.9-15.17,32.78-34.99,47.48Z"
//    ).toNodes()
//    val bubbleChat2 = PathParser().parsePathString(
//        "M125.13,210.68c.21-4.48,2.3-8.43,3.45-12.58,3.05-11.08,4.05-22.45,5.97-33.69,1.56-9.14,2.81-18.34,2.88-27.63,.05-6.14-1.95-8.14-7.91-7.98-18.95,.53-37.92-.04-56.7-2.18-18.5-2.11-36.59-6.54-51.63-18.62C1.88,92.5-5.09,73.41,3.85,49.82c4.43-11.7,13.85-20.06,24.6-26.52C75.68-5.04,224.99-13.34,291.92,31.84c9.43,6.36,14.5,16.19,14.18,44.75-.86,5.91-2.74,11.56-4.85,17.17-5.16,13.73-15.8,21.61-28.79,26.29-14.93,5.38-30.19,9.44-46.28,10.64-21.53,1.6-43.04,2.96-64.62,2.85-4.38-.02-6.41,.97-7.15,5.84-1.39,9.14-.69,18.5-3.89,27.47-5.78,16.2-14.22,30.74-25.4,43.81Z"
//    ).toNodes()
//}
//
//val largeRadialGradientGreen = object : ShaderBrush() {
//    override fun createShader(size: Size): Shader {
//        val biggerDimension = maxOf(size.height, size.width)
//        return RadialGradientShader(
//            colors = listOf(Color(0xFFECEA8E), Color(0xFF414141)),
//            center = size.center,
//            radius = biggerDimension / 2.8f,
//            colorStops = listOf(0.1f, 1f)
//        )
//    }
//}
//
//@Language("AGSL")
//val PERLIN_NOISE = """
//    uniform float2 resolution;
//    uniform float time;
//    uniform shader contents;
//
//    //
//    // Description : Array and textureless GLSL 2D/3D/4D simplex
//    //               noise functions.
//    //      Author : Ian McEwan, Ashima Arts.
//    //  Maintainer : stegu
//    //     Lastmod : 20201014 (stegu)
//    //     License : Copyright (C) 2011 Ashima Arts. All rights reserved.
//    //               Distributed under the MIT License. See LICENSE file.
//    //               https://github.com/ashima/webgl-noise
//    //               https://github.com/stegu/webgl-noise
//    //
//
//    vec3 mod289(vec3 x) {
//      return x - floor(x * (1.0 / 289.0)) * 289.0;
//    }
//
//    vec4 mod289(vec4 x) {
//      return x - floor(x * (1.0 / 289.0)) * 289.0;
//    }
//
//    vec4 permute(vec4 x) {
//         return mod289(((x*34.0)+10.0)*x);
//    }
//
//    float snoise(vec3 v)
//    {
//      const vec2  C = vec2(1.0/6.0, 1.0/3.0) ;
//      const vec4  D = vec4(0.0, 0.5, 1.0, 2.0);
//
//       // First corner
//      vec3 i  = floor(v + dot(v, C.yyy) );
//      vec3 x0 =   v - i + dot(i, C.xxx) ;
//
//      // Other corners
//      vec3 g = step(x0.yzx, x0.xyz);
//      vec3 l = 1.0 - g;
//      vec3 i1 = min( g.xyz, l.zxy );
//      vec3 i2 = max( g.xyz, l.zxy );
//
//      //   x0 = x0 - 0.0 + 0.0 * C.xxx;
//      //   x1 = x0 - i1  + 1.0 * C.xxx;
//      //   x2 = x0 - i2  + 2.0 * C.xxx;
//      //   x3 = x0 - 1.0 + 3.0 * C.xxx;
//      vec3 x1 = x0 - i1 + C.xxx;
//      vec3 x2 = x0 - i2 + C.yyy; // 2.0*C.x = 1/3 = C.y
//      vec3 x3 = x0 - D.yyy;      // -1.0+3.0*C.x = -0.5 = -D.y
//
//      // Permutations
//      i = mod289(i);
//      vec4 p = permute( permute( permute(
//                 i.z + vec4(0.0, i1.z, i2.z, 1.0 ))
//               + i.y + vec4(0.0, i1.y, i2.y, 1.0 ))
//               + i.x + vec4(0.0, i1.x, i2.x, 1.0 ));
//
//      // Gradients: 7x7 points over a square, mapped onto an octahedron.
//      // The ring size 17*17 = 289 is close to a multiple of 49 (49*6 = 294)
//      float n_ = 0.142857142857; // 1.0/7.0
//      vec3  ns = n_ * D.wyz - D.xzx;
//
//      vec4 j = p - 49.0 * floor(p * ns.z * ns.z);  //  mod(p,7*7)
//
//      vec4 x_ = floor(j * ns.z);
//      vec4 y_ = floor(j - 7.0 * x_ );    // mod(j,N)
//
//      vec4 x = x_ *ns.x + ns.yyyy;
//      vec4 y = y_ *ns.x + ns.yyyy;
//      vec4 h = 1.0 - abs(x) - abs(y);
//
//      vec4 b0 = vec4( x.xy, y.xy );
//      vec4 b1 = vec4( x.zw, y.zw );
//
//      vec4 s0 = floor(b0)*2.0 + 1.0;
//      vec4 s1 = floor(b1)*2.0 + 1.0;
//      vec4 sh = -step(h, vec4(0.0));
//
//      vec4 a0 = b0.xzyw + s0.xzyw*sh.xxyy ;
//      vec4 a1 = b1.xzyw + s1.xzyw*sh.zzww ;
//
//      vec3 p0 = vec3(a0.xy,h.x);
//      vec3 p1 = vec3(a0.zw,h.y);
//      vec3 p2 = vec3(a1.xy,h.z);
//      vec3 p3 = vec3(a1.zw,h.w);
//
//      //Normalise gradients
//      vec4 norm = inversesqrt(vec4(dot(p0,p0), dot(p1,p1), dot(p2, p2), dot(p3,p3)));
//      p0 *= norm.x;
//      p1 *= norm.y;
//      p2 *= norm.z;
//      p3 *= norm.w;
//
//      // Mix final noise value
//      vec4 m = max(0.5 - vec4(dot(x0,x0), dot(x1,x1), dot(x2,x2), dot(x3,x3)), 0.0);
//      m = m * m;
//      return 105.0 * dot( m*m, vec4( dot(p0,x0), dot(p1,x1),
//                                    dot(p2,x2), dot(p3,x3) ) );
//    }
//
//    half4 main(in vec2 fragCoord) {
//        vec2 uv = (fragCoord.xy / resolution.xy);
//
//        float noise = snoise(vec3(uv.x * 6, uv.y * 6, time * 0.5));
//
//        noise *= exp(-length(abs(uv * 1.5)));
//        vec2 offset1 = vec2(noise * 0.02);
//        vec2 offset2 = vec2(0.02) / resolution.xy;
//        uv += offset1 - offset2;
//
//        return contents.eval(uv * resolution.xy);
//    }
//""".trimIndent()