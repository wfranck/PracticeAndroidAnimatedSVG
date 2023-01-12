package com.example.testborrarbeta

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testborrarbeta.ui.theme.TestBorrarBetaTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

import com.example.testborrarbeta.OctupusPaths.tentacleA
import com.example.testborrarbeta.OctupusPaths.tentacleB
import com.example.testborrarbeta.OctupusPaths.tentacleC
import com.example.testborrarbeta.OctupusPaths.tentacleD
import com.example.testborrarbeta.OctupusPaths.tentacleE
import com.example.testborrarbeta.OctupusPaths.tentacleF
import com.example.testborrarbeta.OctupusPaths.tentaclePalmA
import com.example.testborrarbeta.OctupusPaths.tentaclePalmB
import com.example.testborrarbeta.OctupusPaths.tentaclePalmC
import com.example.testborrarbeta.OctupusPaths.tentaclePalmD
import com.example.testborrarbeta.OctupusPaths.tentaclePalmE
import com.example.testborrarbeta.OctupusPaths.tentaclePalmF
import com.example.testborrarbeta.OctupusPaths.tentaclePalmG
import com.example.testborrarbeta.OctupusPaths.tentaclePalmH
import com.example.testborrarbeta.OctupusPaths.tentaclePalmI
import com.example.testborrarbeta.OctupusPaths.head1
import com.example.testborrarbeta.OctupusPaths.head2
import com.example.testborrarbeta.OctupusPaths.head3
import com.example.testborrarbeta.OctupusPaths.head4
import com.example.testborrarbeta.OctupusPaths.eyeWhite1
import com.example.testborrarbeta.OctupusPaths.eyeWhite2
import com.example.testborrarbeta.OctupusPaths.eyeBlack1
import com.example.testborrarbeta.OctupusPaths.eyeBlack2

import com.example.testborrarbeta.OctupusPaths.suckers

import com.example.testborrarbeta.JellyFishPaths.bubble1
import com.example.testborrarbeta.JellyFishPaths.bubble2
import com.example.testborrarbeta.JellyFishPaths.bubble3
import com.example.testborrarbeta.JellyFishPaths.bubble4
import com.example.testborrarbeta.JellyFishPaths.bubble5
import com.example.testborrarbeta.JellyFishPaths.face
import com.example.testborrarbeta.JellyFishPaths.freckle1
import com.example.testborrarbeta.JellyFishPaths.freckle2
import com.example.testborrarbeta.JellyFishPaths.freckle3
import com.example.testborrarbeta.JellyFishPaths.freckle4
import com.example.testborrarbeta.JellyFishPaths.leftEye
import com.example.testborrarbeta.JellyFishPaths.mouth
import com.example.testborrarbeta.JellyFishPaths.outerJelly
import com.example.testborrarbeta.JellyFishPaths.rightEye
import com.example.testborrarbeta.JellyFishPaths.tentacle2
import com.example.testborrarbeta.JellyFishPaths.tentacle3
import com.example.testborrarbeta.JellyFishPaths.tentacle4
import com.example.testborrarbeta.JellyFishPaths.tentacle5
import com.example.testborrarbeta.JellyFishPaths.tentacle6
import com.example.testborrarbeta.JellyFishPaths.tentacle7
import com.example.testborrarbeta.JellyFishPaths.tentacle8
import com.example.testborrarbeta.JellyFishPaths.tentacle9
import com.example.testborrarbeta.JellyFishPaths.tentaclePath

import com.example.testborrarbeta.ThisIsFinePaths.dog
import com.example.testborrarbeta.ThisIsFinePaths.dogBack
import com.example.testborrarbeta.ThisIsFinePaths.eyes
import com.example.testborrarbeta.ThisIsFinePaths.teeths
import com.example.testborrarbeta.ThisIsFinePaths.tongue
import com.example.testborrarbeta.ThisIsFinePaths.hat
import com.example.testborrarbeta.ThisIsFinePaths.furniture
import com.example.testborrarbeta.ThisIsFinePaths.furniture2
import com.example.testborrarbeta.ThisIsFinePaths.cup
import com.example.testborrarbeta.ThisIsFinePaths.smokeBack
import com.example.testborrarbeta.ThisIsFinePaths.smoke
import com.example.testborrarbeta.ThisIsFinePaths.fire0
import com.example.testborrarbeta.ThisIsFinePaths.fireBack
import com.example.testborrarbeta.ThisIsFinePaths.fire1
import com.example.testborrarbeta.ThisIsFinePaths.fireBack2
import com.example.testborrarbeta.ThisIsFinePaths.fire2
import com.example.testborrarbeta.ThisIsFinePaths.fire3
import com.example.testborrarbeta.ThisIsFinePaths.bubbleChat
import com.example.testborrarbeta.ThisIsFinePaths.bubbleChat2

//import com.example.testborrarbeta.ThisIsFinePaths.smoke
//import com.example.testborrarbeta.ThisIsFinePaths.fire1
//import com.example.testborrarbeta.ThisIsFinePaths.fire2
//import com.example.testborrarbeta.ThisIsFinePaths.fire3
//import com.example.testborrarbeta.ThisIsFinePaths.fire4
//import com.example.testborrarbeta.ThisIsFinePaths.fireLine1
//import com.example.testborrarbeta.ThisIsFinePaths.fireLine2
//import com.example.testborrarbeta.ThisIsFinePaths.fireLine3
//import com.example.testborrarbeta.ThisIsFinePaths.fireLine4
//import com.example.testborrarbeta.ThisIsFinePaths.furniture1
//import com.example.testborrarbeta.ThisIsFinePaths.furniture2
//import com.example.testborrarbeta.ThisIsFinePaths.cup

import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestBorrarBetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //Greeting("Android")
                    //JellyfishAnimatione()
                    //PerlinNoiseUI()

                    TheOctopusEscapes()
                    //ThisIsFine()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}


@OptIn(ExperimentalTextApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview
fun ThisIsFine() {
    val vectorSmokeBack = rememberVectorPainter(
        defaultWidth = 590.46f.dp,
        defaultHeight = 653.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 655.1f,
        autoMirror = true,
    ) { _, _ ->
        val duration = 3000
        val transition = rememberInfiniteTransition()
        val translationY by transition.animateFloat(
            initialValue = 0f,
            targetValue = 20f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = EaseInOut),
                repeatMode = RepeatMode.Reverse
            )
        )

        Group(name = "smokeBack", translationY = 120f + translationY, translationX = 50f) {
            Path(
                smokeBack,
                fill = SolidColor(Color(0xFF18100C)),
                fillAlpha = 1f
            )
        }
        Group(name = "smoke", translationY = 127f + translationY, translationX = 57f) {
            Path(
                smoke,
                fill = SolidColor(Color(0xFFA1735E)),
                fillAlpha = 1f
            )
        }
    }

    val vectorFire = rememberVectorPainter(
        defaultWidth = 590.46f.dp,
        defaultHeight = 653.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 655.1f,
        autoMirror = true,
    ) { _, _ ->
        Group(name = "fire0", translationY = 260f, translationX = 60f) {
            Path(
                fire0,
                fill = SolidColor(Color(0xFFF5AD46)),
                fillAlpha = 0.7f
            )
        }
        Group(name = "fireBack", translationY = 280f, translationX = 50f) {
            Path(
                fireBack,
                fill = SolidColor(Color(0xFF18100C)),
                fillAlpha = 1f
            )
        }
        Group(name = "fire1", translationY = 290f, translationX = 54f) {
            Path(
                fire1,
                fill = SolidColor(Color(0xFFF5AD46)),
                fillAlpha = 1f
            )
        }
        Group(name = "fireBack2", translationY = 328f, translationX = 54f) {
            Path(
                fireBack2,
                fill = SolidColor(Color(0xFF18100C)),
                fillAlpha = 1f
            )
        }
        Group(name = "fire2", translationY = 358f, translationX = 58f) {
            Path(
                fire2,
                fill = SolidColor(Color(0xFFFFCF4F)),
                fillAlpha = 1f
            )
        }
        Group(name = "fire3", translationY = 500f, translationX = 70f) {
            Path(
                fire3,
                fill= SolidColor(Color(0xFFF5AD46)),
                fillAlpha = 1f
            )
        }
    }

    val vectorFixed = rememberVectorPainter(
        defaultWidth = 590.46f.dp,
        defaultHeight = 653.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 655.1f,
        autoMirror = true,
    ) { _, _ ->
        Group(name = "bubbleChat", translationY = 85f, translationX = 150f) {
            Path(
                bubbleChat,
                fill = SolidColor(Color(0xFF18100C)),
                fillAlpha = 1f
            )
        }
        Group(name = "bubbleChat2", translationY = 94f, translationX = 157f) {
            Path(
                bubbleChat2,
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 1f
            )
        }
        Group(name = "dog", translationY = 260f, translationX = 54f) {
            Path(
                dogBack,
                fill = SolidColor(Color(0xFF18100C)),
                fillAlpha = 1f
            )
        }
        Group(name = "dog", translationY = 323f, translationX = 103f) {
            Path(
                dog,
                fill = SolidColor(Color(0xFFE59825)),
                fillAlpha = 1f
            )
        }
        Group(name = "hat", translationY = 263f, translationX = 110f) {
            Path(
                hat,
                fill = SolidColor(Color(0xFFE59825)),
                fillAlpha = 1f
            )
        }
        Group(name = "dogeyes", translationY = 265f, translationX = 110f) {
            Path(
                eyes,
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 1f
            )
        }
        Group(name = "dogteeths", translationY = 261f, translationX = 111f) {
            Path(
                teeths,
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 1f
            )
        }
        Group(name = "dogtongue", translationY = 265f, translationX = 112f) {
            Path(
                tongue,
                fill = SolidColor(Color(0xFFE46948)),
                fillAlpha = 1f
            )
        }
        Group(name = "dogfurniture", translationY = 485f, translationX = 59f) {
            Path(
                furniture,
                fill = SolidColor(Color(0xFFC98825)),
                fillAlpha = 1f
            )
        }
        Group(name = "dogfurniture2", translationY = 557f, translationX = 361f) {
            Path(
                furniture2,
                fill = SolidColor(Color(0xFFC98825)),
                fillAlpha = 1f
            )
        }
        Group(name = "cup", translationY = 495f, translationX = 412f) {
            Path(
                cup,
                fill = SolidColor(Color(0xFFEECF9E)),
                fillAlpha = 1f
            )
        }
    }

    val time by produceState(55f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it.toFloat() / 200f
            }
        }
    }
    val shader = RuntimeShader(PERLIN_NOISE)

    Image(
        vectorFire, contentDescription = "fire",
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradientGreen)
            .onSizeChanged { size ->
                shader.setFloatUniform(
                    "resolution",
                    size.width.toFloat(),
                    size.height.toFloat()
                )
            }
            .graphicsLayer {
                shader.setFloatUniform("time", time)
                renderEffect = RenderEffect
                    .createRuntimeShaderEffect(
                        shader,
                        "contents"
                    )
                    .asComposeRenderEffect()
            }
    )
    Image(
        vectorSmokeBack, contentDescription = "smoke",
        modifier = Modifier
            .fillMaxSize()

    )
    Image(
        vectorFixed, contentDescription = "dog and bubble",
        modifier = Modifier
            .fillMaxSize()
    )

    Column() {
        Text(
            "THIS IS FINE.",
            modifier = Modifier
                .padding(top = 250.dp)
                .padding(start = 165.dp),
            style = TextStyle(
                brush = SolidColor(Color(0xFF18100C)),
                fontWeight = FontWeight.ExtraBold, fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.unkempt)),
                textAlign = TextAlign.Center
            )
        )
    }
}

object ThisIsFinePaths {
    val dog = PathParser().parsePathString(
        "M238.83,116.38h0c-7.85,4.33-15.69,8.46-24.93,9.28-10.06,.89-21.12-4.49-24.26-12.87-3.1-8.25-3.2-16.6,1.4-24.45,2.08-3.56,4.71-6.8,7.09-10.18,.48-.68,1.08-1.47,.37-2.19-.38-.39-1.33-.62-1.84-.42-6.92,2.63-14.62,2.48-21.19,6.6-5.35,3.36-10.01,7.14-14.4,11.79-12.81,13.55-32.63,7.83-44.44-1.79-2.42-1.97-3.11-2.5-4.05,.8-2.98,10.52-9.21,18.57-18.68,24.1-3.99,.75-7.84,2.27-11.98,2.16h0s0,0,0,0c-.63-.16-.91,.19-1.05,.73-8.56,.63-16.71-.89-23.76-5.84-14.86-10.45-20.52-29.87-14.48-47.89,3.98-11.87,8.42-23.14,22.19-27.12,3.72-1.08,7.61-.67,11.2-2.05,14.18,1.03,26.1,6.21,33.71,19.07,1.67-1.73,2.29-3.18,2.15-4.93,.97-1.73,1.94-3.46,2.92-5.19,3.16-7.8,8.28-13.92,15.55-18.21,0,0,0,0,0,0,0,0,0,0,0,0,3.9,.97,6.52-2.75,10.33-2.09,.11-.4,.23-.8,.34-1.2-1.37-1.14-2.74-2.28-4.11-3.42,0,0,0,0,0,0,0,0,0,0,0,0-2.29-4.62-6.26-7.68-10.11-10.84h0s0,0,0,0c-1.64-1.27-3.28-2.54-4.92-3.81-1.33-1.18-2.47-2.81-4.03-3.45-3.07-1.27-6.46-5.28-9.6-1.16-2.17,2.85-4.82,4.78-7.46,6.92h0s0,0,0,0c-.98-.03-1.32,.73-1.74,1.4-2.4,1.12-4.79,2.24-7.19,3.36h0c-3.29,2.04-7.16,2.49-10.71,3.82-3.03,.78-6.36,.1-9.2,1.84-1.6,.39-3.19,.78-4.79,1.16-7.13,.67-14.26,.72-21.4-.01-.82,0-1.66,.13-2.46-.01-3.03-.53-5.93-3.19-8.81-2.23-3.37,1.12-1.53,5.54-3.27,8.01-.09,.13-.1,.31-.16,.47-1.66,4.14-3.32,8.27-4.98,12.41-.37,4.57,.45,8.96,1.97,13.24h0s0,0,0,0c.64,5.39,1.27,10.78,1.91,16.17,.16,4.98-.79,10.12,1.98,14.75h0c.28,.18,.32,.41,.14,.69-.44,6.84,.01,13.61,1.76,20.26h0c1.23,5.8-1.11,11.34-1.21,17.06-.1,6.16-3.52,11.73-3.49,17.96,0,0,0,0,0,0s0,0,0,0c-2.27,2.22-2.58,5.46-4.2,8.02-3.46,4.32-6.5,8.99-11.28,12.12-1.72,1.13-2.45,3.13-2.56,5.11-.2,3.64-1.39,7.03-2.17,10.53h0c-1.6,5.04-3.2,10.08-4.8,15.12h0c-6.07,22.08-4.76,44.2-.37,66.35,.3,1.49,.66,2.56,2.37,2.56,7.38,0,14.76,0,22.92,0-7.39-16.46-9.02-32.97-7.82-49.88,.2-2.75-.96-5.62,.75-8.24,.25-.76,.43-1.52,.52-2.27,1.21-6.21,.85-12.71,3.45-18.66,2.49,1.68,.71,4.02,.93,6.05,0,0,.2,.14,.2,.14v.05c-.07-.06-.13-.13-.2-.19-1.56,9.98-.58,20.04-.79,30.06-.11,5.39-.32,10.86,1.76,16.04,0,0,0,0,0,0-.07,5.02,1.26,9.75,2.82,14.47,4.31,13.08,4.26,13.01,17.85,12.5,3.29-.12,3.56-1,2.3-3.78-5.09-11.24-7.81-23.11-9.06-35.34,.56-12.94-.88-25.95,1.17-38.84,.49-1.07-.17-2.71,1.78-3.25,1.11,2.42,.97,4.93,.77,7.48-.09,1.17,.2,2.04,1.52,.83,.93,3.3-2.17,5.71-1.44,9.46,1.31,6.73,.93,13.77,1.34,20.68,.13,2.26-.38,4.65,1.04,6.71,.63,10.84,3.89,20.99,7.69,31.08,1.4,3.71,3.02,5.09,7.09,5.06,25.95-.21,51.91-.12,77.86-.15q13.12-.02,17.4-11.83h0s0,0,0,0c.97-2.98,1.51-6.04,1.69-9.17h0c2.03-16.27-2.7-31.45-7.51-46.62-.61-1.91-1.54-2-3.36-1.28-4.94,1.96-10.07,3.3-15.45,3.15-1.23-.03-3.12,.56-3.38-1.17-.36-2.41,1.9-1.58,3.17-1.92,1.91-.52,3.91-.81,5.73-1.56,5.1-2.11,11.22-1.81,15.27-6.41,2.92-2.53,5.8-5.09,6.68-9.11,.79-4.75,.99-9.54,.97-14.35,0-1.34,.05-2.82-1.75-3.06-1.88-.24-3,.97-2.87,2.61,.26,3.49-1.65,6.33-2.42,9.49h0c-6.29,10.45-15.52,13.84-27.21,10.34-14.51-4.34-25.41-13.25-33.63-25.73-1.36-2.06-2.46-4.96-4.4-5.81-2.29-1-4.02,2.23-6.19,3.26-.69,.33-1.59,.23-2.39,.32h0s0,0,0,0c.37-2.05,1.39-3.71,3.03-4.99,5.83-3.84,10.96-8.68,17.2-11.94h0c1.64-.95,3.27-1.89,4.91-2.84,.67-.38,1.34-.76,2-1.14,0,0,0,0,0,0,2.85-.89,5.66-2.51,8.85-.84-.79,2.52-4.12,1.75-4.6,4.22,10.86,7.93,23.78,10.54,36.16,14.5,5.39,1.73,10.9,3,16.55,2.68,13.55-.76,27.24-.5,40.47-4.04,8.07-2.16,16.48-3.57,23.57-8.51,12.2-5.84,17.89-16.21,20.9-30.06-4.35,3.62-7.73,6.44-11.12,9.26Zm-186.15,4.84c-.12-.18-.23-.33-.34-.46,.06,.02,.11,.04,.18,.07,.08,.13,.13,.26,.16,.39Zm28.84,45.09c-.33,.08-.58,.02-.78-.14,.24,.02,.5,.06,.78,.14Z"
    ).toNodes()
    val dogBack = PathParser().parsePathString(
        "M421.86,290.41c.48-4.4,.77-9.73,.92-15.42,.02,2.38,.06,3.89,.12,3.89,24-.04,21.74-4.75,22.14-20.21,.4-15.46-14.58-17.88-22.14-16.51,0,0-.06,6.37-.1,13.8-.22-10.42-.77-19.92-1.43-25.08-.8-6.19-70.08,4.34-70.08,4.34l.09,3.93h-.04s.03,.01,.04,.02l1.36,56.87s.54,.66,2.08,1.6c-35.01,7.67-54.79,16.47-58.85,28.52h-87.21c5.22-9.22,15.58-34.91-3.2-71.87,0,0,16.98-4.99,13.64-28.64,0,0,78.81,2.49,85.66-51.12,0,0,26.53-29.85-4.13-47.88-7.87-4.63-34.1-3.35-47.24,10.99l-30.45,8.36c22.46-41.75-24.9-53.33-24.9-53.33-6.52-14.95-21.09-27.44-31.65-29.93,0,0,11.07-40.59-24.64-31.11,0,0-23.94-44.59-56.42-27.84-31.83,16.41-11.82,51.84-11.82,51.84-26.56,6.47-30.75,34.07,.27,42.7,6.52,1.81-69.27,55.69-51.99,107.26,2.86,8.53,6.12,14.44,9.61,18.54-.61,.47-1.21,.95-1.8,1.45-2.04,9.55-3.61,19.26-4.83,28.72,1.21-10.55,2.78-20.58,4.83-28.72,.47-.46,.96-.87,1.46-1.21,0,0-16.37-5.06-31.18,5.88l.61,96.85,20.29-.08,29.16,.08c.24,.58,.48,1.16,.73,1.74l155.54,1.09h237.88v-39.57s-8.99-.28-22.36,.08Z"
    ).toNodes()
    val eyes = PathParser().parsePathString(
        "M149.97,97.94c5.58,2.25,9.48,6.72,11.74,12.16-1.58-9.62-6.81-16.43-17.96-19.45-20.54-5.57-32.09,7.53-37.04,26.1-.43,1.63-.77,3.27-1.01,4.88l-5.12,1.17c-5.26-12.24-18.13-19.4-32.46-19.46-23.15-.09-31.88,17.01-31.88,37.46s14.61,34.7,32.63,34.7,33.48-12.49,34.21-39.01c.14-5.04-.79-9.68-2.51-13.7l5.12-1.17c3.69,6.09,5.12,13.53,3.26,21.64,3.62,6.61,9.82,11.25,18.33,12.73,12.85,2.24,23.63-1.08,29.69-12.88-6.32,6.75-16.52,9.91-24.72,6.6-10.72-4.33-13.39-20.23-9.18-32.25,5.27-15.04,15.37-24.17,26.9-19.51Zm-71.81,72.86c-12.08,.75-23.23-13.1-23.23-26.54s6.24-27.39,19.23-28.21c12.5-.79,25.51,13.07,25.51,29.17,0,17.44-9.68,24.85-21.5,25.58Z"
    ).toNodes()
    val teeths = PathParser().parsePathString(
        "M140.13,214.05c-.74-.22-2.35-.69-4.23-1.22-1.1-.31-2.1-.59-2.9-.81-.55-.42-1.34-.59-2.05-.74-3.55-.73-7-1.74-10.33-3.18-.18-.08-.92-.31-1.08-.37-.62-.26-1.75-.65-3.72-1.4-1.72-.66-3.17-1.32-3.94-1.6-.99-.68-2.6-.93-3.66-1.41-3.22-1.45-6.05-3.39-8.39-5.99-1.64-1.82-3.07-2.77-5.35-.79-2.27,1.98-2.19,3.88-.46,5.98,3.29,3.99,7.33,6.6,13.21,6.72,1.93,.3,4.87-.04,5.45-2.34,.24-.97-.23-1.73-.8-2.18,.81,.32,2.37,.99,3.94,1.6,1.54,.59,2.89,1.1,3.72,1.4-1.92-.44-2.42,.84-2.3,3.04,.2,3.76,3.51,6.28,8.74,6.38,1.86,.39,3.57-.69,5.39-1.45,1.1-.46,2.33-.85,2.34-2.19,0-.64-.36-1.19-.7-1.5,.77,.22,1.88,.52,2.9,.81,1.68,.48,3.4,1,4.23,1.22-.51-.06-1.3-.14-1.79-.18-1.79-.11-1.67,1.74-1.49,3.05,.27,2.01,1.34,3.71,3.52,3.2,1.88-.44,3.35-1.96,4.32-3.9-1.29-1.41-2.91-1.94-4.55-2.18Z"
    ).toNodes()
    val hat = PathParser().parsePathString(
        "M98.07,29.07c-2.61-2.72-5.79-1.77-8.88-1.35-6.28,3.35-10.49,8.91-15.07,14.08,0,0,0,0,0,0,0,0,0,0,0,0-3.79,3.33-7.39,6.93-11.43,9.93-6.32,4.68-12.69,8.41-21.54,6.36-5.86-1.35-11.54-3.42-17.49-4.49l-2.66-7.29c5.4-2.67,10.74-5.47,16.24-7.92,10.8-4.82,22.39-7.21,33.68-10.51,4.09-.41,5.59-2.02,2.77-5.78-.55-.73-.83-1.67-1.24-2.51,0,0,0,0,0,0C67.61,7.73,53.68-.61,39.81,.04c-8.9,.42-19.1,9.19-20.71,17.81-1.8,3.01-.82,6.56-1.87,9.71-1.25,3.02-1.05,6.02,.09,9.01,1.29,3.27,.26,7.24,2.98,10.09l3.21,6.91c-.47-.08-.94-.17-1.41-.24-7.05-1.04-13.36-.38-18.33,5.41C-.03,63.18-.73,68.43,.67,73.7c1.51,5.68,6.69,8.26,11.43,10.81,6.72,3.62,7.73,3.13,10.63-3.84,.3-.73,.86-1.35,1.3-2.02,1.35-1.72,.74-2.98-.96-3.93-1.56-.87-3.25-1.45-4.87-2.22,3.09,1.45,6.31,2.31,9.73,2.25,4.92,2.04,10.19,1.5,15.31,2.08,4.99,.58,9.98,.71,14.96-.09,1.26-.36,2.52-.73,3.78-1.09,3.66-1.05,7.75,.42,11.2-1.89,1.26-.65,2.51-1.3,3.77-1.95,7.79-1.65,14.82-5.22,20.18-10.85,7.66-8.05,12.63-21.74,.94-31.89Z"
    ).toNodes()
    val tongue = PathParser().parsePathString(
        "M112.66,226.7c.06,5.34,0,5.38-4.59,2.86-10.13-5.56-17.24-14.25-24.09-23.23-2.42-3.17,.94-3.44,2.28-4.64,1.5-1.34,2.88-2.72,4.57,.24,3.43,6,8.8,9.32,15.74,9.85,1.75,.13,2.65,.81,3.2,2.47,1.34,4.07,3.06,8.03,2.89,12.45Z"
    ).toNodes()
    val furniture = PathParser().parsePathString(
        "M23.67,8.11c.37-.39-.81-.74-.41-1.03,0,0-11.68-4.65-23.25,4.73L.48,94.78l15.03-.07S17.59,5.99,23.21,7.81c-.68,6.07-3.8,86.97-3.8,86.97h20.7c-9.41-45.85,3.51-83.11,5.99-88.69,3.92-8.85-11.96-7.97-22.44,2.02Z"
    ).toNodes()
    val furniture2 = PathParser().parsePathString(
        "M131.36,27H0C.85,17.33,31.73,7.98,75.54,8.95c61.55,1.36,19.01-8.95,55.81-8.95V27Z"
    ).toNodes()
    val cup = PathParser().parsePathString(
        "M63.66,11.33l-.06,8.48c5.42,.97,11.38,7.17-.04,12,0,0,.01,4.5,.04,5.74l-4.92-.25c.21-13.66-.39-28.87-1.21-35.6C56.8-3.7,0,5.48,0,5.48l.03,1.16,57.31,.56c.28,11.4-46.78,20.23-57.34,4.51L1.19,58.53s8.1,10.56,53.69,5.13c2.44-.29,3.51-11.77,3.77-25.04h4.98c0,.1,.02,.16,.03,.16,17.95-.03,16.26-3.55,16.56-15.11,.3-11.56-10.9-13.37-16.56-12.34Z"
    ).toNodes()

    val smokeBack = PathParser().parsePathString(
        "M459.18,88.93V35.72h0V.14L1.34,0l-.26,23.29m0,.19L0,119.04s46.65,19.21,79.5-3.83c5.87-4.12,30.33,37.28,103.16,10.16l7.23-6.05c71.17,42.84,124.93,8.21,124.93,8.21,60.28,42.58,87.19,10.95,104.16,3.28,17.64,6.07,40.2,4.55,40.2,4.55v-46.16m-2.05,23.13c.64,0,1.19,.01,1.62,.06l-1.62-.06"
    ).toNodes()
    val smoke = PathParser().parsePathString(
        "M445.85,77.67V44.03c-1.51,.27-14.92,.67-17.77-2.61-2.72-3.13,4.77,1.14,17.77,2.53v-12.75l-8.95,.49c.55-1.94,8.18-2.06,8.95-.9V.12L1.3,0l-.15,22.31c24.4,8.15,61.06-7.57,61.06-7.57-1.98,5.94-42.57,17.6-61.22,10.12L0,103.97s45.29,16.78,77.19-3.35c5.7-3.6,29.45,32.56,100.16,8.88l7.02-5.28c69.1,37.42,121.3,7.17,121.3,7.17,58.53,37.19,84.66,9.57,101.14,2.86,17.12,5.3,39.04,3.97,39.04,3.97v-21.75l-1.99,1.64c-4.1,3.66-15.77,2.85-28.36-1l26.91-1.75c-.42,.17,3.07,1.08,3.44,1.12v-14.88c-12.2-.59-54.73-6.89-49.19-12.19,0,0,16.28,8.19,49.19,8.26ZM38.75,51.04c21.97,1.88,25.45,3.69,51.8-3.62s10.08-1.11-2.85,2.64c-12.93,3.75-28.3,8.27-49.23,3.82s.28-2.85,.28-2.85Zm45.71,40.23c-7.42-3.14,.1-2.01,.1-2.01,7.79,1.32,9.03,2.6,18.37-2.55,9.35-5.15,3.58-.78-1.01,1.86-4.59,2.65-10.04,5.84-17.46,2.7Zm22.88,1.76c-2.04,5.3-6.73,4.72-10.65,4.26s-9.45-1.88-1.61-1.14c7.85,.74,7.75-.76,11.04-4.72,0,0,3.26-3.7,1.22,1.6Zm-.29-26.27c-2.83,1.9-7.76,4.81-12.26,7.18-11.24,5.93-24.6,13.05-44.14,9.36-19.54-3.69-.11-3.62-.11-3.62,20.16,.35,23.54,2.31,46.5-9.33,1.72-.87,3.2-1.61,4.52-2.26-4.24,.94-9.52,1.97-14.41,2.77-17.15,2.8-37.53,6.22-63.04-4.05-25.51-10.27,.96-3.96,.96-3.96,27.37,6.85,31.38,10.06,66.24,4.83,7.68-1.15,12.6-1.78,15.43-2.02,2.85-1.23,3.19-1.07,2.01-.12,2.07-.04,1.05,.47-1.71,1.22Z"
    ).toNodes()


    val fire0 = PathParser().parsePathString(
        "M368.84,7.82c3.35,30-35.01,95.78-35.01,95.78,0,0-7.75-5.11-14.17-2.35-7.57-18.82-30.19-73.39-27.07-45.43,3.35,30-35.01,95.78-35.01,95.78,0,0-24.53-61.66-20.63-15.92,3.35,39.41-12.38,77.2-21.89,95.96-11.69-16.51-19.99-34.59-19.07-52.05,2.74-51.88-19.59,63.07-19.59,63.07,0,0-39.7-49.03-55.62-74.75-15.55-25.13,1.58,54.23,2.38,57.9-3.12-4.67-63.09-95.45-37.17-141.97,25.37-45.53-45.47,48.35-45.47,48.35,0,0-14.1-61.2-17.07-91.2C20.47,10.98,0,94.27,0,94.27L123.18,225.88s.02,.07,.02,.07l.09,.04,.02,.02s0-.01-.01-.02l156.43,68.45s-38.26-26.71-63.08-60.6l94.68-113.63c-.16,3.4-.07,7.34,.32,11.92,1.22,14.3,.02,25.45-2.43,34.14l89.11-106.96s-33.22-84.99-29.48-51.49Z"
    ).toNodes()
    val fireBack = PathParser().parsePathString(
        "M2.38,308.12L0,60.83S12.19,31.84,24.54,26.73s-17.84,9.38,2.45,61.47c11.65,29.91-5.35-12.74,19.33-43.15C80.85,2.49,25.39,54.54,63.14,125.46c37.75,70.92-7.13-3.6,20.43-44.73,24.91-37.18-21.77,21.26,51.14,107.37,58.63,69.24-.73-55.51,9.92-46.06,17.64,15.64,30.66,29.79,33.83,45.72,3.18,15.93,16.93-5.44,13.95-16.93-2.98-11.48,27.09,45.95,33.88,77.36s7.65-77.69,50.24-124.28c42.58-46.58,48.57-54.33,49.79-74.51,1.22-20.18,6.49,26.81,8.67,60.54s-6.38-5.43,29.43-34.43c35.81-29,48.51-39.36,52.82-62.48,4.51-24.18,5.11-15.22,22.31,31.39,16.31,44.2,4.72,258.7,4.72,258.7L2.38,308.12Z"
    ).toNodes()
    val fire1 = PathParser().parsePathString(
        "M2.34,291.13L0,57.47S11.97,30.09,24.11,25.25c12.13-4.83-17.52,8.87,2.41,58.08,11.45,28.26-5.26-12.04,18.98-40.77C79.42,2.36,24.94,51.53,62.02,118.54c37.08,67.01-7-3.4,20.07-42.27,24.47-35.13-21.38,20.09,50.24,101.45,57.58,65.42-.72-52.45,9.75-43.52,17.32,14.78,30.11,28.15,33.23,43.2,3.12,15.05,16.63-5.14,13.7-16-2.93-10.85,26.61,43.42,33.28,73.1,6.67,29.68,7.52-73.41,49.34-117.43,41.83-44.01,47.7-51.33,48.9-70.4s6.38,25.33,8.52,57.2c2.14,31.87-6.26-5.13,28.9-32.53,35.17-27.4,47.65-37.19,51.88-59.04,4.43-22.84,5.02-14.38,21.92,29.66,16.02,41.76,4.63,244.44,4.63,244.44L2.34,291.13Z"
    ).toNodes()
    val fireBack2 = PathParser().parsePathString(
        "M1.54,64.17s4.82-23.14,9.55-31.28c4.73-8.14,4.75,28.85,18.76,52.04,0,0,.73-12.85,13.02-43.72,.98-2.46,6.8,23.94,14.99,53.87,0,0,16.69,39,62.82,91.52,46.13,52.52,28.3,0,28.3,0,0,0,16.88,11.85,21.42,37.16s42.25-28.88,39.74-28.88,22.42,33.16,29.94,36.79,35.97-108.69,64.68-152.53c28.7-43.84,6.61-4.08,32.06,28.2,13.51,17.14,18.81-34.23,63.17-83.15,0,0-6.74,41.57-5.69,48.93s34.79-45.54,32.19-68.01c-2.6-22.46,10.88,36.13,17.94,40.55s0,212.53,0,212.53L0,258.88,1.54,64.17Z"
    ).toNodes()
    val fire2 = PathParser().parsePathString(
        "M434.37,39.74c-6.9-3.85-20.07-54.84-17.53-35.29,2.54,19.55-30.44,65.6-31.47,59.19-1.02-6.41,5.56-42.58,5.56-42.58-43.35,42.58-48.53,87.29-61.74,72.37-24.87-28.1-3.28-62.7-31.34-24.55-28.05,38.15-55.86,135.91-63.21,132.75-6.7-2.88-27.52-27.09-29.31-31.37-2.48,5.58-34.67,44.98-38.8,24.48-4.44-22.03-20.94-32.34-20.94-32.34,0,0,17.42,45.71-27.66,0-45.08-45.71-61.4-79.65-61.4-79.65-8-26.04-13.69-49.02-14.65-46.88-12.01,26.87-12.72,38.05-12.72,38.05C15.48,53.74,15.46,21.55,10.83,28.63,6.21,35.71,1.5,55.85,1.5,55.85L0,225.32l434.37-.6s6.9-181.13,0-184.98Zm-125.95,102.2h1.44l-10.23,40.21h-1.68l10.47-40.21Zm-3.45,53.18h-.25l-11.12,11.13-1.07-.37,11-10.82,9.04-21.95h1.25l-8.86,22.01Zm87.9-80.62l11.45-25.96h1.58l-11.19,25.96h-1.84Zm27.96-22.21l-.05,.09-.35,1.05-.22,.03-13.26,24.8h-2.29l13.62-24.88,9.34-26.99,1.94-.22-8.75,26.12h.02Z"
    ).toNodes()
    val fire3 = PathParser().parsePathString(
        "M325.18,82.69s-22.04-52.87-24.6-37.74c-2.56,15.13-31.59,31.98-31.59,31.98,0,0,28.05-42.36,3.03-17.73-17.47,17.2-48.58,22.52-65.78,24.17-44.7-2.8-120.43-12.8-141.51-48.16-30.47-51.12,3.69,36.78,3.69,36.78,0,0-35.36-34.96-38.47-66.35C26.84-25.75,0,83.95,0,83.95l234.33,.49c-4.27-.04-10.78-.18-18.84-.55l109.68-1.19Z"
    ).toNodes()

    val bubbleChat = PathParser().parsePathString(
        "M125.64,232.8c.22-4.96,1.94-10.11,3.14-14.7,3.2-12.24,5.96-24.72,7.97-37.13,1.64-10.1,3.06-19.45,3.14-29.72,.05-6.79-3.65-8.97-9.91-8.78-19.89,.59-33.86-.18-53.57-2.55-19.41-2.33-38.4-7.23-54.18-20.58C1.97,102.21-5.34,81.11,4.04,55.05c4.65-12.93,14.54-22.17,25.82-29.3C79.42-5.57,236.11-14.74,306.35,35.19c9.89,7.03,15.21,17.89,14.89,49.45-.91,6.54-2.87,12.77-5.09,18.98-5.41,15.17-16.58,23.87-30.21,29.05-15.67,5.94-31.68,10.43-48.56,11.76-22.59,1.77-38.35,3.26-61,3.14-4.59-.02-7.41,2.65-8.19,8.04-1.46,10.1-4.2,19.8-7.56,29.72-6.07,17.9-15.17,32.78-34.99,47.48Z"
    ).toNodes()
    val bubbleChat2 = PathParser().parsePathString(
        "M125.13,210.68c.21-4.48,2.3-8.43,3.45-12.58,3.05-11.08,4.05-22.45,5.97-33.69,1.56-9.14,2.81-18.34,2.88-27.63,.05-6.14-1.95-8.14-7.91-7.98-18.95,.53-37.92-.04-56.7-2.18-18.5-2.11-36.59-6.54-51.63-18.62C1.88,92.5-5.09,73.41,3.85,49.82c4.43-11.7,13.85-20.06,24.6-26.52C75.68-5.04,224.99-13.34,291.92,31.84c9.43,6.36,14.5,16.19,14.18,44.75-.86,5.91-2.74,11.56-4.85,17.17-5.16,13.73-15.8,21.61-28.79,26.29-14.93,5.38-30.19,9.44-46.28,10.64-21.53,1.6-43.04,2.96-64.62,2.85-4.38-.02-6.41,.97-7.15,5.84-1.39,9.14-.69,18.5-3.89,27.47-5.78,16.2-14.22,30.74-25.4,43.81Z"
    ).toNodes()
}


val solidWhite = SolidColor(Color.White)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun JellyfishAnimatione() {
    val blinkAlphaAnimation = remember {
        Animatable(1f)
    }
    val blinkScaleAnimation = remember {
        Animatable(1f)
    }

    suspend fun instantBlinkAnimation() {
        val tweenSpec = tween<Float>(150, easing = LinearEasing)
        coroutineScope {
            launch {
                blinkAlphaAnimation.animateTo(0f, animationSpec = tweenSpec)
                blinkAlphaAnimation.animateTo(1f, animationSpec = tweenSpec)
            }
            launch {
                blinkScaleAnimation.animateTo(0f, animationSpec = tweenSpec)
                blinkScaleAnimation.animateTo(1f, animationSpec = tweenSpec)
            }
        }
    }

    val vectorPainterFace = rememberVectorPainter(
        defaultWidth = 530.46f.dp,
        defaultHeight = 563.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 563.1f,
        autoMirror = true,
    ) { _, _ ->
        val duration = 3000
        val transition = rememberInfiniteTransition()
        val translationY by transition.animateFloat(
            initialValue = 0f,
            targetValue = -50f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = EaseInOut),
                repeatMode = RepeatMode.Reverse
            )
        )

        Group(name = "bubbles") {
            Path(
                bubble1,
                fill = solidWhite,
                fillAlpha = 0.67f
            )

            Path(
                bubble2,
                fill = solidWhite,
                fillAlpha = 0.75f
            )

            Path(
                bubble3,
                fill = solidWhite,
                fillAlpha = 0.89f
            )

            Path(
                bubble4,
                fill = solidWhite,
                fillAlpha = 0.77f
            )

            Path(
                bubble5,
                fill = solidWhite,
                fillAlpha = 0.77f
            )
        }
        Group(name = "face", translationY = translationY) {
            Group(
                name = "eye-left",
                scaleY = blinkScaleAnimation.value,
                pivotY = 233f
            ) {
                Path(
                    leftEye,
                    fill = SolidColor(Color(0xFFb4bebf)),
                    fillAlpha = blinkAlphaAnimation.value
                )
            }
            Group(
                name = "right-eye",
                scaleY = blinkScaleAnimation.value,
                pivotY = 233f
            ) {
                Path(
                    rightEye,
                    fill = SolidColor(Color(0xFFb4bebf)),
                    fillAlpha = blinkAlphaAnimation.value
                )
            }

            Path(
                mouth,
                fill = SolidColor(Color(0xFFd3d3d3)),
                fillAlpha = 0.72f
            )
        }
    }

    val vectorPainter = rememberVectorPainter(
        defaultWidth = 530.46f.dp,
        defaultHeight = 563.1f.dp,
        viewportHeight = 563.1f,
        viewportWidth = 530.46f,
        autoMirror = true,
    ) { _, _ ->
        val duration = 3000
        val transition = rememberInfiniteTransition()
        val translationY by transition.animateFloat(
            initialValue = 0f,
            targetValue = -50f,
            animationSpec = infiniteRepeatable(
                tween(duration, easing = EaseInOut),
                repeatMode = RepeatMode.Reverse
            )
        )
        Group(name = "jellyfish", translationY = translationY) {
            Group(name = "tentacles") {
                Path(
                    pathData = tentaclePath,
                    fill = solidWhite,
                    fillAlpha = 0.49f
                )
                Path(
                    tentacle2,
                    fill = solidWhite,
                    fillAlpha = 0.66f
                )
                Path(
                    tentacle3,
                    fill = solidWhite,
                    fillAlpha = 0.45f
                )
                Path(
                    tentacle4,
                    fill = solidWhite,
                    fillAlpha = 0.6f
                )
                Path(
                    tentacle5,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle6,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle7,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle8,
                    fill = solidWhite,
                    fillAlpha = 1f
                )
                Path(
                    tentacle9,
                    fill = solidWhite
                )
            }
            Group(name = "body") {
                Path(
                    face,
                    fill = solidWhite
                )
                Path(
                    outerJelly,
                    fill = solidWhite,
                    fillAlpha = 0.5f
                )
            }
            Group(name = "freckles") {
                Path(
                    freckle1,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )
                Path(
                    freckle2,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )
                Path(
                    freckle3,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )
                Path(
                    freckle4,
                    fill = SolidColor(Color(0xfff0dfe2)),
                )

            }
        }
    }

    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it.toFloat() / 2000f
            }
        }
    }

    val shader = RuntimeShader(PERLIN_NOISE)

    Image(
        vectorPainter, contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
            .onSizeChanged { size ->
                shader.setFloatUniform(
                    "resolution",
                    size.width.toFloat(),
                    size.height.toFloat()
                )
            }
            .graphicsLayer {
                shader.setFloatUniform("time", time)
                renderEffect = RenderEffect
                    .createRuntimeShaderEffect(
                        shader,
                        "contents"
                    )
                    .asComposeRenderEffect()
            }
    )



    val coroutineScope = rememberCoroutineScope()
    Image(
        vectorPainterFace, contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    coroutineScope.launch {
                        instantBlinkAnimation()
                    }
                }
            }
    )
}

object JellyFishPaths {
    val freckle2 = PathParser().parsePathString(
        """
                M322.89 215.5a3.94 3.94 0 0 0-1.32 3.61 3 3 0 1 0 5.91 0c.27-3.81-2.76-4.98-4.59-3.61z
            """.trimIndent()
    ).toNodes()
    val freckle1 = PathParser().parsePathString(
        """
                M330 225.23a4.16 4.16 0 0 0-.1.55c-.52 3.79 3.9 6.73 5.91 3 2.42-4.46-4.66-8.14-5.81-3.55z
            """.trimIndent()
    ).toNodes()
    val freckle3 = PathParser().parsePathString(
        """
                M206.88 241.24c-.88 2-.11 4.56 3.36 4.27 3.87-.33 5.42-5.85.75-6.62a3.79 3.79 0 0 0-4.11 2.35z
            """.trimIndent()
    ).toNodes()
    val freckle4 = PathParser().parsePathString(
        """
                M206.88 241.24c-.88 2-.11 4.56 3.36 4.27 3.87-.33 5.42-5.85.75-6.62a3.79 3.79 0 0 0-4.11 2.35z
            """.trimIndent()
    ).toNodes()
    val bubble1 = PathParser().parsePathString(
        """
                    M384.85 167.41c-7.37 4.94-6.92 18 4.34 16.82a19.89 19.89 0 0 0 3.34-.71c13.54-4.17 9.8-20.35-3.66-17.77a10.52 10.52 0 0 0-4.02 1.66z
                """.trimIndent()
    ).toNodes()
    val bubble2 = PathParser().parsePathString(
        """
                    M130.5 266.76c-6 4-5.59 14.59 3.51 13.61a15.22 15.22 0 0 0 2.7-.57c11-3.38 7.93-16.47-3-14.38a8.85 8.85 0 0 0-3.21 1.34z
                """.trimIndent()
    ).toNodes()
    val bubble3 = PathParser().parsePathString(
        """
                    M424.53 152.72c2.41-6-5.11-13.82-12-7.41s2.41 15.31 9.3 10.64a7.19 7.19 0 0 0 2.7-3.23z
                """.trimIndent()
    ).toNodes()
    val bubble4 = PathParser().parsePathString(
        """
                    M392.89 142.46c1.65-4.09-3.5-9.45-8.22-5.07s1.65 10.47 6.36 7.28a4.87 4.87 0 0 0 1.86-2.21z
                """.trimIndent()
    ).toNodes()
    val bubble5 = PathParser().parsePathString(
        """
                    M114 242.88c-2-.26-4.34 1.09-3.68 4.42 1.68 8.05 11.35-3.48 3.68-4.42z
                """.trimIndent()
    ).toNodes()
    val leftEye =
        PathParser().parsePathString("M262 233.63a3.1 3.1 0 1 0-3 3.19 3.1 3.1 0 0 0 3-3.19z").toNodes()
    val rightEye =
        PathParser().parsePathString("M294 232.72a3.1 3.1 0 1 0-3 3.18 3.1 3.1 0 0 0 3-3.18z").toNodes()

    val mouth = PathParser().parsePathString(
        "M286.84 244s-.11-.12-.32-.31a9.91 9.91 0 0 1-.81-.76l-.5-.53c-.16-.2-.31-.41-.48-.61s-.27-.46-.41-.69a6.83 6.83 0 0 1-.29-.75 5.86 5.86 0 0 1-.06-3 7.71 7.71 0 0 1 .29-1.05 3.46 3.46 0 0 1 .13-.39.49.49 0 1 0-.8-.55 3.31 3.31 0 0 0-.33.38 5.42 5.42 0 0 0-.68 1.12 5.32 5.32 0 0 0-.53 2.93 4.34 4.34 0 0 0 .07.52h-.08a9.59 9.59 0 0 1-1.64.53 11.21 11.21 0 0 1-1.15.22 10.31 10.31 0 0 1-1.29.17 12 12 0 0 1-1.41.1c-.48 0-1.07.05-1.48 0l-1.46-.06c-.48 0-.94-.11-1.39-.15s-.88-.17-1.29-.23-.78-.2-1.13-.29-.65-.22-.93-.31a5 5 0 0 1-.67-.28 2.56 2.56 0 0 0 0-.27 5.08 5.08 0 0 0 0-1.1 6 6 0 0 0-1.12-3 2.65 2.65 0 0 0-.32-.39.5.5 0 0 0-.67-.15.49.49 0 0 0-.15.68 3.33 3.33 0 0 1 .12.39 9.13 9.13 0 0 1 .26 1.06 5.81 5.81 0 0 1-.14 3 6.81 6.81 0 0 1-.31.74l-.43.67c-.18.2-.34.41-.49.61l-.52.51a9 9 0 0 1-.83.74c-.21.18-.33.3-.33.3a.49.49 0 0 0 .5.81s.16-.05.44-.17a6.14 6.14 0 0 0 1.15-.56c.22-.15.48-.3.72-.49s.49-.43.73-.68.45-.57.67-.87.24-.49.35-.74a2.77 2.77 0 0 0 .42.27 7 7 0 0 0 1 .53 7.33 7.33 0 0 0 1.23.48 8.56 8.56 0 0 0 1.41.38c.5.08 1 .2 1.54.24l1.6.09c.61 0 1.05 0 1.58-.07a11.79 11.79 0 0 0 1.53-.18 9.58 9.58 0 0 0 1.44-.31 8.32 8.32 0 0 0 1.25-.42 6.92 6.92 0 0 0 1-.47 5.28 5.28 0 0 0 .6-.36c.09.19.18.37.26.55s.42.62.64.89.48.49.71.71.49.35.71.51a7.4 7.4 0 0 0 1.13.59l.44.18a.47.47 0 0 0 .51-.11.48.48 0 0 0 .01-.6z"
    ).toNodes()

    val face =
        PathParser().parsePathString("M283.26 178.86c-17.72-1.84-36.33 5.33-52.24 17.37a110.1 110" +
                ".1 0 0 0-22.14 22.06c-8.54 11.67-14 31.93-7.48 47.29 8.82 20.73 29.67 14.17 44" +
                ".32 15.6 14.34 1.55 28.68-.52 43-.52 10.47 0 20.94 1 31.4-.51 9.36-1.39 21.38-9" +
                ".12 24-22.07 4.24-21-13.77-45.52-24.27-58.37-10.69-13.1-23.39-19.48-36.59-20" +
                ".85z").toNodes()
    val outerJelly =
        PathParser().parsePathString("M340.52 295.71c-10.12 1.19-15.63-.12-20.38-1.64-3.63-1.17-6" +
                ".84-2.44-11.37-2.83l-1-.06c-1-.06-2.05-.06-3.17 0a21.37 21.37 0 0 0-8 2.28c-1.27" +
                ".59-2.6 1.21-4.09 1.82a42.23 42.23 0 0 1-10.6 3c-1 .16-2 .29-3.11.37-1.56.14-3" +
                ".26.22-5.09.22h-.51c-1.74 0-3.35-.08-4.84-.2-.6 0-1.15-.08-1.7-.15a47.37 47.37 0" +
                " 0 1-9.43-1.8 8.25 8.25 0 0 1-.92-.29 75 75 0 0 1-8-3.34L248 293a19.06 19.06 0 0" +
                " 0-6.86-1.81c-1.27-.06-2.44-.12-3.56-.14h-1a51.6 51.6 0 0 0-9.87.67c-8 1.38-10" +
                ".86 4.29-22.82 4.06-22.13 0-9.51-11.13-11-17.8-7.24-33.11-.69-63.69 18-83.91 14" +
                ".65-15.86 35.74-24.25 61-24.25 23.56 0 43.94 8.81 59 25.44 19.21 21.29 27 53.84 " +
                "20 83-1.67 6.46 10.68 14.95-10.37 17.45z").toNodes()

    val tentaclePath = PathParser().parsePathString(
        "M226.31 258.64c.77 8.68 2.71 16.48 1.55 25 .15-.78 8.24-5 15.18-7.37 23-3.1 10.84-4.65 22.55 1.17 32.52 4.65 7.37 7.75 11.71 5.81 21.25-2.33 8.67-7.37 16.91-2.71 26 4.26 8.68 7.75 4.34 8.14-3 .39-12.14 0-24.28.77-36 .78-16.91-12-27.75-2.71-44.23 7-12.15 11.24-33 7.76-46.83z"
    ).toNodes()
    val tentacle2 =
        PathParser().parsePathString("M246.47 259.51a75 75 0 0 1 3.1 33.39c-1.55 11.71-8.91 20" +
                ".82-8.91 33 0 10 3.48 18.21 5.81 27.75 2.71 10.41.39 19.51-.78 30.35-1.55 9.11-4" +
                ".65 24.29-.77 33.39s7.37 4.77 7.37-2.6c0-6.07-.78-11.71-1.17-17.77-.39-8.25 3" +
                ".1-12.58 5-20.39 5.43-21.24-9.3-42.06-3.49-63.31 2.72-11.27 10.86-22.55 11.25-34" +
                ".26.38-6.07-1.55-14.74-2.72-20.38z").toNodes()
    val tentacle3 =
        PathParser().parsePathString("M276.54 262.11c-1.55 9.11 2.21 17.35-.51 26.89-3.87 12.57-8" +
                ".2 23.41-8.58 37.29 0 10.41 5.78 15.61 7.72 25.15 2.72 11.28-2.73 19.95-5.44 30" +
                ".35-1.94 6.51-3.11 15.62-.79 22.12 3.49 8.24 5.43 7.81 3.88 18.21-.78 4.77-2.72 " +
                "17.35 4.26 19.09 5.43.86 5.82-6.94 5.82-11.72-.39-12.57-3.49-24.28-3.11-37.29" +
                ".39-14.31 4.66-27.75 5.82-42.06.39-7.37-1.94-13-3.49-19.95-2.33-10.4-.78-15.17 3" +
                ".1-24.71 4.65-10.85 5.28-21.25 6.06-33 .77-6.07 2.53-13-.54-18.65v.44z").toNodes()
    val tentacle4 =
        PathParser().parsePathString("M300.34 261.25c1.55 14.3 7 19.94 1.17 34.68-5.43 12.15-8.53" +
                " 22.12-3.88 35.13 3.88 10.84 5.43 18.21 3.88 30.36-1.56 10-2.33 19.94 4.26 28.61" +
                " 13.57 17.35 10.86-10.4 8.14-19.07-1.55-6.08-3.49-11.71-2.71-18.22 1.16-8.24 3" +
                ".88-14.31 1.55-23-1.55-6.07-5-10.41-3.88-17.35s5.82-12.14 6.59-19.51c1.17-7.37 3" +
                ".49-30.35-3.1-35.56z").toNodes()

    val tentacle5 =
        PathParser().parsePathString("M254.44 253.48c-8.78 25.12-15.81 53.28 2.61 77.35 11.06 14.46 42.58 51.91 38.16 69.8-3.91 15.83-20.78 24.73-14.17 28.18 8.22 3.21 22.2-26 18.7-41.25-3.89-16.94-17.68-35.47-34.38-54.78-2.48-2.86-13.15-18.09-14-35.58-1-19.92 12.84-42.61 12.38-42.62-3.13-.02-8.1-4.53-9.3-1.1z")
            .toNodes()
    val tentacle6 =
        PathParser().parsePathString("M276.84 253.3c7.24 20.7 13 43.91-2.15 63.75-9.11 11.91-35.1 42.78-31.45 57.53 3.22 13.05 13.8 19.9 11.68 23.22-2.87 3.86-16.15-16.76-16.55-25.86-.72-16.2 12.73-33.92 29.47-53.28 2-2.36 10.84-14.91 11.57-29.33.82-16.41-10.59-35.12-10.2-35.12 2.54-.02 6.64-3.74 7.63-.91z")
            .toNodes()
    val tentacle7 =
        PathParser().parsePathString("M295.27 254.27c5.83 16.69 12.69 18.82 1.58 35.22a45 45 0 0 0-6.67 19.22c-.52 6.52 6.09 13.33 5.5 19.6-2.35 24.92-17.13 46.55-19.33 44.29-4.35-4.6 22.07-28.6 12.46-51.16-8.38-19.64 6.49-36.53 9.9-42.49 2.75-4.8-11.51-24.51-11.42-24.51 2.05-.01 5.93-.6 7.98-.17z")
            .toNodes()
    val tentacle8 =
        PathParser().parsePathString("M256.56 252.22c-7 6.09-17.21 14-23.17 24-5.55 9.25-7.06 20.43-7.66 27.88-.53 6.52 2.21 13.76 5.49 19.6 14 24.92-15.62 46.55-19.33 44.29-4.55-4 14.59-16.95 17.41-23.45 2.91-6.69-.07-16.29-5-27.71-8.38-19.64 1-39.25 4.37-45.2 2.75-4.81 19.82-19 19.91-19 2.1-.07 5.92-.84 7.98-.41z")
            .toNodes()
    val tentacle9 =
        PathParser().parsePathString("M288.76 254.4c7 6.09 17.21 14 23.17 24 5.55 9.25 7.06 20.43 7.66 27.88.53 6.52-2.21 13.76-5.49 19.6-14 24.93 9 45 19.33 44.29 6.58-4.07-14.59-16.95-17.41-23.45-2.91-6.69.07-16.28 5-27.71 8.38-19.64-1-39.25-4.37-45.2-2.75-4.81-19.82-19-19.91-19-2.1-.06-5.92-.81-7.98-.41z")
            .toNodes()
}


@OptIn(ExperimentalTextApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview
fun PerlinNoiseUI() {
    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 1000f
            }
        }
    }

    val shader = RuntimeShader(PERLIN_NOISE)
    Column(Modifier
        .padding(10.dp)
        .fillMaxSize()
        .onSizeChanged { size ->
            shader.setFloatUniform(
                "resolution",
                size.width.toFloat(),
                size.height.toFloat()
            )
        }
        .graphicsLayer {
            shader.setFloatUniform("time", time)
            renderEffect = RenderEffect
                .createRuntimeShaderEffect(
                    shader,
                    "contents"
                )
                .asComposeRenderEffect()
        }
    ) {
        Text(
            "  The \nOctupus \nEscapes",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                brush = Brush.linearGradient(
                            listOf(Color(0xFFC9E7F8),Color(0xFF687D8A))),
                fontWeight = FontWeight.Bold, fontSize = 90.sp,
                fontFamily = FontFamily(Font(R.font.unkempt))
            )
        )
    }
}

val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFF12A0DB), Color(0xFF000000)),
            center = size.center,
            radius = biggerDimension / 2.8f,
            colorStops = listOf(0f, 1f)
        )
    }
}

val largeRadialGradientGreen = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xFFECEA8E), Color(0xFF414141)),
            center = size.center,
            radius = biggerDimension / 2.8f,
            colorStops = listOf(0.1f, 1f)
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
@Preview(device = Devices.FOLDABLE)
fun TheOctopusEscapes() {
    val blinkAlphaAnimation = remember {
        Animatable(1f)
    }
    val blinkScaleAnimation = remember {
        Animatable(1f)
    }

    suspend fun instantBlinkAnimation() {
        val tweenSpec = tween<Float>(100, easing = LinearEasing)
        coroutineScope {
            launch {
                blinkAlphaAnimation.animateTo(0f, animationSpec = tweenSpec)
                blinkAlphaAnimation.animateTo(1f, animationSpec = tweenSpec)
            }
            launch {
                blinkScaleAnimation.animateTo(0f, animationSpec = tweenSpec)
                blinkScaleAnimation.animateTo(1f, animationSpec = tweenSpec)
            }
        }
    }

    val colorsTentacles = arrayOf(
        0f to Color(0xFFc64332),
        0.3f to Color(0xFFdf6c3d),
        0.4f to Color(0xFFcb4531),
        0.9f to Color(0xFFd24d33)
    )
    val colorsTentaclesOther = arrayOf(
        0f to Color(0xFFc64332),
        0.9f to Color(0xFFEC4625)
    )
    val colorsTentaclesBack = arrayOf(
        0f to Color(0xFF8D372B),
        0.9f to Color(0xFF752A1B)
    )
    val colorsTentaclesPalm = arrayOf(
        0.3f to Color(0xFFebb79b),
        0.8f to Color(0xFFe0885c)
    )



    val vectorPainterTentacles = rememberVectorPainter(
        defaultWidth = 590.46f.dp,
        defaultHeight = 653.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 655.1f,
        autoMirror = true,
    ) { _, _ ->
        Group(name = "octupus", translationY = -55f) {
            Group(name = "tentacles") {
                Path(
                    tentacleA,
                    fill = Brush.linearGradient(colorStops = colorsTentacles),
                    fillAlpha = 1f
                )
                Path(
                    tentacleB,
                    fill = Brush.linearGradient(colorStops = colorsTentacles),
                    fillAlpha = 1f
                )
                Path(
                    tentacleC,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesBack),
                    fillAlpha = 1f
                )
                Path(
                    tentacleD,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesOther),
                    fillAlpha = 1f
                )
                Path(
                    tentacleE,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesOther),
                    fillAlpha = 1f
                )
                Path(
                    tentacleF,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesOther),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmA,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmB,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmC,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmD,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmE,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmF,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmG,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmH,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Path(
                    tentaclePalmI,
                    fill = Brush.linearGradient(colorStops = colorsTentaclesPalm),
                    fillAlpha = 1f
                )
                Group(name = "suckers", translationY = 35f) {
                    Path(
                        suckers,
                        fill = Brush.linearGradient(colorStops = colorsTentaclesBack),
                        fillAlpha = 1f,

                        )
                }
            }
        }


    }

    val vectorPainterHead = rememberVectorPainter(
        defaultWidth = 590.46f.dp,
        defaultHeight = 653.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 655.1f,
        autoMirror = true,
    ) { _, _ ->
        Group(name = "head", translationY = 300f) {
            Path(
                head1,
                fill = Brush.linearGradient(colorStops = colorsTentacles),
                fillAlpha = 1f
            )
            Path(
                head2,
                fill = Brush.linearGradient(colorStops = colorsTentacles),
                fillAlpha = 1f
            )
            Path(
                head3,
                fill = Brush.linearGradient(colorStops = colorsTentacles),
                fillAlpha = 1f
            )
            Path(
                head4,
                fill = Brush.linearGradient(colorStops = colorsTentacles),
                fillAlpha = 1f
            )
        }
        Group(name = "bubbles") {
//            Path(
//                bubbleA,
//                fill = SolidColor(Color(0xFFb4bebf)),
//                fillAlpha = 0.67f
//            )
        }
    }

    val vectorPainterEyes = rememberVectorPainter(
        defaultWidth = 590.46f.dp,
        defaultHeight = 653.1f.dp,
        viewportWidth = 530.46f,
        viewportHeight = 655.1f,
        autoMirror = true,
    ) { _, _ ->
        Group(name = "head", translationY = 390f, translationX = 90f) {
            Group(
                name = "eye-left",
                scaleY = blinkScaleAnimation.value,
                pivotY = 70f
            ) {
                Path(
                    eyeWhite1,
                    fill = SolidColor(Color.White),
                    fillAlpha = blinkAlphaAnimation.value,
                )
                Path(
                    eyeWhite2,
                    fill = SolidColor(Color.White),
                    fillAlpha = blinkAlphaAnimation.value
                )
            }
            Group(
                name = "eye-right",
                scaleY = blinkScaleAnimation.value,
                pivotY = 70f
            ) {
                Path(
                    eyeBlack1,
                    fill = SolidColor(Color.Black),
                    fillAlpha = blinkAlphaAnimation.value
                )
                Path(
                    eyeBlack2,
                    fill = SolidColor(Color.Black),
                    fillAlpha = blinkAlphaAnimation.value
                )
            }
        }
    }

    val time by produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it.toFloat() / 800f
            }
        }
    }
    val shader = RuntimeShader(PERLIN_NOISE)

    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput("dragging") {
                detectDragGestures { change, dragAmount ->
                    pointerOffset += dragAmount
                }
            }
            .onSizeChanged {
                pointerOffset = Offset(it.width / 2f, it.height / 2f)
            }
            .drawWithContent {
                drawContent()
                // draws a fully black area with a small keyhole at pointerOffset that’ll show part of the UI.
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, Color.Black),
                        center = pointerOffset,
                        radius = 200.dp.toPx(),
                    )
                )
            }
    ) {
        Image(
            vectorPainterTentacles, contentDescription = "Tentacle",
            modifier = Modifier
                .fillMaxSize()
                .background(largeRadialGradient)
                .onSizeChanged { size ->
                    shader.setFloatUniform(
                        "resolution",
                        size.width.toFloat(),
                        size.height.toFloat()
                    )
                }
                .graphicsLayer {
                    shader.setFloatUniform("time", time)
                    renderEffect = RenderEffect
                        .createRuntimeShaderEffect(
                            shader,
                            "contents"
                        )
                        .asComposeRenderEffect()
                }
        )

        Image(
            vectorPainterHead, contentDescription = "Head",
            modifier = Modifier
                .fillMaxSize()
        )

        val coroutineScope = rememberCoroutineScope()
        Image(
            vectorPainterEyes, contentDescription = "Eyes",
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        coroutineScope.launch {
                            instantBlinkAnimation()
                        }
                    }
                }
        )
    }

        Column(Modifier
            //.padding(100.dp)
            //.fillMaxSize()
            .onSizeChanged { size ->
                shader.setFloatUniform(
                    "resolution",
                    size.width.toFloat(),
                    size.height.toFloat()
                )
            }
            .graphicsLayer {
                shader.setFloatUniform("time", time)
                renderEffect = RenderEffect
                    .createRuntimeShaderEffect(
                        shader,
                        "contents"
                    )
                    .asComposeRenderEffect()
            }
        ) {
            Text(
                "    The \nOctupus \n Escapes",
                modifier = Modifier
                    .padding(top = 55.dp)
                    .padding(start = 100.dp),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        listOf(Color(0xFFC9E7F8), Color(0xFF687D8A))
                    ),
                    fontWeight = FontWeight.Bold, fontSize = 85.sp,
                    fontFamily = FontFamily(Font(R.font.unkempt))
                )
            )

            Text(
                "Maile \n Meloy",
                modifier = Modifier
                    .padding(top = 120.dp)
                    .padding(start = 335.dp),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        listOf(Color(0xFFC9E7F8), Color(0xFF687D8A))
                    ),
                    fontWeight = FontWeight.Bold, fontSize = 40.sp,
                    fontFamily = FontFamily(Font(R.font.unkempt)),
                    textAlign = TextAlign.Center
                )
            )

            Text(
                "pictures by",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(start = 355.dp),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        listOf(Color(0xFFC9E7F8), Color(0xFF687D8A))
                    ),
                    fontWeight = FontWeight.Bold, fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.unkempt)),
                    textAlign = TextAlign.Center
                )
            )
            Text(
                "Felicita \n Sala",
                modifier = Modifier.padding(start = 355.dp),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        listOf(Color(0xFFC9E7F8), Color(0xFF687D8A))
                    ),
                    fontWeight = FontWeight.Bold, fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.unkempt)),
                    textAlign = TextAlign.Center
                )
            )
        }
}


object OctupusPaths {
    val tentacleA = PathParser().parsePathString(
        "M0,273.31v-47.98s34.14-17.19,28.83-64.33-14.98-86.6-3.38-117.13S65.01-.28,81.95,0s32.49,6.71,50.57,26.21,33.06,43.31,20.5,63.78-32.84,28.26-52.66,16.63-24.63-36.73-8.21-44.96,20.81-8.66,28.04-3.85,12.13,18.32-.95,30.41c0,0,2.21-30.67-12.5-21.78s-15.34,21.82,3.15,22.53,39.91-39.89,2.61-47.44-64.23,26.32-49.79,48.83,26.75,57.74,8.15,115.8C53.63,259.91,0,282.49,0,282.49v-9.18Z"
    ).toNodes()
    val tentacleB = PathParser().parsePathString(
        "M545.08,112.27v-47.45h-74.32s-54.73,33.6-78.57,53.9-53.48,12.89-58.95,4.51-26.74-10.95-23.84-72.81,4.19-55.41-5.15-39.95-30.6,57.02-14.17,103.41,45.1,52.17,59.92,52.17c28.31,0,44.03-6.92,99.87-46.05,50.58-35.44,95.23-7.73,95.23-7.73Z"
    ).toNodes()
    val tentacleC = PathParser().parsePathString(
        "M570.85,226.13v104.07s-15.75,70.2,17.68,158.9c21.02,55.76-38.43,192.58-95.69,241.14s-131.19,0-131.19,0c0,0,197.31-102.99,137.82-269.63s71.37-234.48,71.37-234.48Z"
    ).toNodes()
    val tentacleD = PathParser().parsePathString(
        "M549.1,413.16l-.75-65.84s-101.77-19.8-101.34-68.58c.28-31.46,31.59-40.6,82.18-73.68,33.65-22,45.44-36.68,45.44-64.84s-46.74-49.5-92.08-34-64.39,57.85-54.87,72.43,31.04,29.36,50.72,11.34,12.76-34.22,4.14-40.62-26.04-24.62-60.82,3.82,.86,40.04,10.4,33.52c1.94-1.33-11.8-6.28-10.92-14.13s6.97-20.92,27.27-22.04,30.56,21.9,16.41,28.11-34.89-10.76-17.59-34.97,85.49-22.82,84.58,0-5.87,44.61-84.58,64.8-58.31,97.32-54.33,105.14,12.35,46.14,82.14,65.74c71.68,20.13,71.38,33.87,74,33.8Z"
    ).toNodes()
    val tentacleE = PathParser().parsePathString(
        "M181.07,366.43c34.15,13.45,62.74,28.8,71.01,48.64,7.81,18.74,26.39,37.93,5.3,90.85s-41.38,73.93-29.79,113.8,71.03,66.68,109.45,58.71,120.32-24.64,109.45-93.5-53.64-63.46-80.46-102.4-20.3-68.42,9.42-118.19,81.18-63.98,102.93-71.37,64.18-7.39,64.18-7.39v56.54s-76.5-18.85-109.84,38.42,20.3,87.38,20.3,87.38c0,0,55.09,30.1,64.51,62.69s31.17,72.37-36.97,149.24-144.24,50.73-144.24,50.73h0s-70.92-1.16-125.8-.08c-35.81,.7-55.64-91.2-113.72-92.8C50.09,636.39,.05,719.75,.05,719.75l-.05-112.35H0c4.96-4.76,26.31-28.48,28.35-30.75,.11-.12,.19-.24,.27-.38,1.9-3.61,23.87-54.78,19.06-35.88-11.15,43.82-41.87,51.09-47.63,53.56"
    ).toNodes()
    val tentacleF = PathParser().parsePathString(
        "M428.23,728.91c34.66-47.34,28.57-120.51,115.71-115.08,91.73,5.71,71.7-48.77-3.25-49.41s-82.54,2.63-118.74,25.83-29.36,58.47-35.22,73.45c-5.86,14.98-33.2,46.87-66.4,44.46s-74.21-15.46-98.13-55.57-49.66-64.27-47.85-124.49c6.68-221.2-182.23-62.85-57.98,100.41,7.48,9.83,17.45,14.42,17.45,14.42h0l75.19,86"
    ).toNodes()
    val tentaclePalmA = PathParser().parsePathString(
        "M547.75,60.4l-.21-7.19h-76.22s-40.04,14.18-74.19,51.23c-27.82,30.18-76.9,16.2-84.31-39.85-7.86-59.46-12.02-80.38-13.63-52.19s-1.29,44.1,2.03,58.63c6.64,29.03,16.79,55.46,41.6,59.97s51.83,2.2,85.97-26.79,86.17-55.09,118.95-43.81Z"
    ).toNodes()
    val tentaclePalmB = PathParser().parsePathString(
        "M547.54,274.38l-.53,55.16s-82.64-19.78-112.34,39.03c-31.1,61.57,12.21,78.25,65.06,116.33s31.14,131.59,4.67,162.03-45.98,44.17-58.7,49.63c0,0,62.62-109.48,56.59-142.86s-21.51-54.97-49.25-74.52c-19.89-14.01-24.15-17.72-26.58-19.41-1.25-.87-30.93-20.55-36-50.66s.57-55.55,39.75-97.35,117.33-39.07,117.33-39.07"
    ).toNodes()
    val tentaclePalmC = PathParser().parsePathString(
        "M446.51,692.53c65.72-35.21,70.55-77.9,70.55-77.9,0,0-50.71-15.62-70.55,77.9"
    ).toNodes()
    val tentaclePalmD = PathParser().parsePathString(
        "M472.36,735.18c126.6-6.91,89.64-114.2,89.64-114.2,0,0-10.66,40.67-113.8,114.45"
    ).toNodes()
    val tentaclePalmE = PathParser().parsePathString(
        "M483.17,623.13c15.54-24.6,90-25.38,90-25.38l-11.17,23.23s-46.03-6.27-78.83,2.15"
    ).toNodes()
    val tentaclePalmF = PathParser().parsePathString(
        "M88.69,711.58c8.94,.02,21.26,.47,66.68-.03,0,0-8.94-27.53-1.69-27.29s14.98,14.98,14.98,14.98l6.52,12.31h34.79s-31.65-30.91-52.19-59.66-46.39-45.18-68.38-33.83-29.04,9.91-56.54,46.39l-27.5,36.48h0c-.9,9.46,0,10.61,0,10.61,1.22,1.58,4.45,2.62,13.24,.02,18.27-29.74,39.35-47.28,51.95-42.77,4.23,1.52,7.39,5.48,8.21,6.52,10.12,12.73,.09,36.23,9.91,36.25Z"
    ).toNodes()
    val tentaclePalmG = PathParser().parsePathString(
        "M3.15,242.94v10.02s30.39-14.97,29.36-54.92c0,0-6.21,29.52-29.36,44.9Z"
    ).toNodes()
    val tentaclePalmH = PathParser().parsePathString(
        "M388.34,267.66s-15.77-50.96,77.78-80.75c90.91-28.95,69.79-79.19,33.43-75.25-31.02,3.37-30.12,2.21-30.12,2.21,0,0,47.15-29.62,74.54-3.18,19.41,18.74-.17,55.2-31.89,69.87-66.86,30.93-112.97,43.21-123.73,87.1Z"
    ).toNodes()
    val tentaclePalmI = PathParser().parsePathString(
        "M0,262.42v7.25s59.8-21.74,71.88-77.32,4.83-68.62-7.25-104.38,3.87-54.12,33.83-57.02,34.79,15.95,28.51,30.93-14.01,21.26-26.58,15.46-6.77-22.71,6.77-25.61,14.5-.48,14.5-.48c0,0-6.28-12.08-19.33-5.32s-29.48,18.85-9.66,36.24,43.01,10.15,52.19-9.18,13.05-40.11-18.85-60.4S65.11-1.91,41.92,18.87s-17.88,66.68-5.8,81.66,29.96,67.17,14.01,98.09-12.8,36.24-50.13,63.79Z"
    ).toNodes()

    val suckers = PathParser().parsePathString(
        "M305.62,26.05c-1.36,1.69-4.24,1.49-5.87-.46-1.4-1.67-1.65-4.11-.04-5.55s4.16-1.67,5.62-.04,1.91,4.03,.29,6.05Z\n" +
                "      M308.96,39.43c-.82,2.23-3.49,3.32-5.46,2.18-1.7-.98-2.53-3.21-1.43-5.32s3.37-3.46,5.11-2.55,2.76,3.02,1.78,5.69Z\n" +
                "      M313.29,54.28c-.92,2.5-3.88,3.74-6.06,2.48-1.87-1.08-2.77-3.57-1.54-5.93s3.76-3.89,5.67-2.89,3.02,3.35,1.92,6.34Z\n" +
                "      M320.94,66.23c-.81,2.32-3.74,3.36-5.99,2.07-1.93-1.11-2.95-3.5-1.81-5.67s3.61-3.51,5.58-2.48,3.19,3.3,2.22,6.08Z\n" +
                "      M331.39,78.14c-.84,2.47-4.1,3.49-6.69,2.02-2.22-1.26-3.46-3.88-2.25-6.19s3.95-3.66,6.22-2.47,3.72,3.68,2.72,6.64Z\n" +
                "      M343.34,84.93c-.78,2.27-3.72,3.23-6.04,1.91-1.99-1.13-3.07-3.51-1.96-5.62s3.59-3.38,5.61-2.32,3.31,3.32,2.38,6.03Z\n" +
                "      M356.63,87.72c-1.18,1.93-3.79,3.21-6.25,1.82-2.12-1.2-3.36-3.55-2.32-5.55s3.41-2.75,5.71-1.97c3.64,1.23,4.08,3.69,2.85,5.7Z\n" +
                "      M373.05,83.38c.7,2.16-.03,4.96-2.69,5.93-2.29,.83-4.88,.24-5.72-1.85s.13-4.38,2.22-5.62c3.3-1.96,5.46-.69,6.19,1.54Z\n" +
                "      M389.97,76.91c.7,2.16-.72,5.1-3.38,6.07-2.29,.83-6.42,.35-7.26-1.75s.13-4.38,2.22-5.62c3.3-1.96,7.7-.94,8.42,1.3Z\n" +
                "      M405.13,68.58c.7,2.16-.12,5.03-2.78,5.99-2.29,.83-7.63,.28-8.46-1.82s.13-4.38,2.22-5.62c3.3-1.96,8.29-.79,9.02,1.44Z\n" +
                "      M417.28,56.56c1.97,1.12,2.86,3.73,1.63,6.04-1.06,1.99-4.76,4.8-6.78,3.81s-2.9-3.22-2.25-5.38c1.01-3.42,5.36-5.62,7.41-4.46Z\n" +
                "      M433.03,46.66c1.72,1.47,2.1,4.2,.45,6.24-1.42,1.75-5.58,3.81-7.38,2.45s-2.23-3.71-1.19-5.71c1.65-3.16,6.33-4.5,8.12-2.97Z\n" +
                "      M445.27,36.9c1.72,1.47,3.29,3.87,.47,6.93-1.53,1.66-5.6,3.11-7.4,1.75s-2.23-3.71-1.19-5.71c1.65-3.16,6.33-4.5,8.12-2.97Z\n" +
                "      M460.8,24.34c5.57,.15,4.51,5.48,1.69,8.54-1.53,1.66-6.9,3.53-8.7,2.17s-3.38-4.76-2.34-6.77c1.65-3.16,6.99-4.01,9.34-3.95Z\n" +
                "      M481,14.12c6.19,.21,5.58,5.48,2.47,8.84-1.68,1.82-7.74,3.64-10.17,2.95-5.53-1.57-3.35-5.26-2.21-7.46,1.8-3.49,7.29-4.41,9.9-4.33Z\n" +
                "      M479.26,15.95c3.37,.12,3.04,2.99,1.35,4.82-.92,.99-4.22,1.98-5.54,1.61-3.02-.85-1.83-2.87-1.21-4.07,.98-1.9,3.98-2.41,5.4-2.36Z\n" +
                "      M459.57,26.75c2.2,.06,1.78,2.16,.67,3.37-.6,.65-2.72,1.4-3.43,.86s-1.33-1.88-.92-2.67c.65-1.25,2.76-1.58,3.69-1.56Z\n" +
                "      M296.12,12.78c2.82,2.36,6.41,.58,5.74,.01\n" +
                "      M404.97,56.42c5.93,2.32,6.91-5.04,5.49-5.6\n" +
                "      M432.54,33.68c4.99,3.96,8.09-2.8,6.9-3.75\n" +
                "      M416.28,46.38c7.64,3.74,11.08-6.52,8.49-7.46\n" +
                "      M494.53,12.13c3.63,8.18,11.88,1.79,11.02-.17\n" +
                "      M523.41,12.65c2.18,4.92,7.15,1.08,6.63-.1\n" +
                "      M491.1,11.79c3.83,13.47,18.31,5.47,17.92,.23\n" +
                "\n" +
                "      M516.33,506.23c-1.54,1.39-4.92,1.44-6.28-.2-1.17-1.41-1.79-4.08-.25-5.46s5.22-1.13,6.45,.23,1.63,4.05,.08,5.44Z\n" +
                "      M518.59,509.25c-3.2,3.83-9.26,3.77-12.56-.2-2.84-3.42-3.83-9.19-.12-12.52s9.92-3.1,12.89,.2,3.63,7.95-.2,12.52Z\n" +
                "      M505.62,480c-1.54,1.39-3.8,1.22-5.16-.42-1.17-1.41-.97-3.6,.58-4.99s3.79-1.41,5.02-.04,1.11,4.06-.43,5.45Z\n" +
                "      M508.68,484.65c-3.2,3.83-8.98,4.05-12.28,.08-2.84-3.42-5.59-11.56-1.88-14.9s10.34-3.83,13.31-.52,4.68,10.77,.85,15.34Z\n" +
                "      M489.23,458.87c-1.75,1.12-3.31,.8-4.67-.85-1.17-1.41-1.22-3.6,.33-4.99s4-1.57,5.02-.04c1.54,2.32,.98,4.82-.68,5.88Z\n" +
                "      M493.02,460.97c-3.2,3.83-7.81,3.89-11.11-.08-2.84-3.42-4.16-7.48-.45-10.81s7.91-3.76,10.88-.46,4.52,6.78,.68,11.36Z\n" +
                "      M475.58,442.97c-1.54,1.39-4.86,.64-6.22-1-1.17-1.41-1.28-3.85,.26-5.24s4.11-1.15,5.33,.21,2.17,4.64,.63,6.03Z\n" +
                "      M477.31,445.58c-3.2,3.83-8.42,1.89-11.32-.72-3.29-2.98-5.24-8.34-1.53-11.68s8.67-2.26,11.64,1.05,5.04,6.78,1.21,11.36Z\n" +
                "      M454.14,425.31c-1.54,1.39-3.8,1.22-5.16-.42-1.17-1.41-.97-3.6,.58-4.99s3.79-1.41,5.02-.04,1.11,4.06-.43,5.45Z\n" +
                "      M457.61,429.36c-2.02,2.11-9.02,3.07-12.32-.91-2.84-3.42-3.15-7.35,.56-10.68s7.73-1.44,10.97,1.59c.94,.88,4.91,5.69,.79,10Z\n" +
                "      M437.34,412.86c-1.52,1.42-3.88,.55-4.81-.61-.53-.67-1.53-2.81-.02-4.24,1.19-1.12,3.18-.75,4.41,.61s1.34,3.38,.42,4.24Z\n" +
                "      M440.27,415.54c-1.86,2.09-5.52,2.06-8.74,.46-2.81-1.4-5.9-7.83-3.91-10.31,2.26-2.82,8.87-2.03,11.4,.87,2.92,3.34,3.03,6.97,1.25,8.98Z\n" +
                "      M420.94,399.44c-1.54,1.39-4.32,.68-5.68-.97-1.17-1.41-1.99-3.6-.44-4.99s4.07-.83,5.29,.53,2.38,4.04,.83,5.43Z\n" +
                "      M423.8,402.62c-3.2,3.83-8.38,2.44-11.68-1.53-2.84-3.42-4.36-7.9-.64-11.23s8.15-1.81,11.11,1.49,5.04,6.7,1.21,11.28Z\n" +
                "      M403.98,377.79c-1.54,1.39-3.75,.28-5.11-1.36-1.17-1.41-1.18-3.95,.36-5.34s4.01-1.05,5.23,.31,1.06,5-.48,6.39Z\n" +
                "      M407.69,380.83c-3.2,3.83-7.49,3.49-10.79-.48-2.84-3.42-4.44-8.42-.72-11.76s7.34-4.03,10.31-.72,5.04,8.39,1.21,12.97Z\n" +
                "      M400.44,352.77c-1.57,1.36-4.71,2.13-6.07,.48-1.17-1.41-.56-5.14,.88-6.64,2.11-2.21,3.99-3.08,5.86-1.69,1.47,1.09,1.99,5.56-.66,7.85Z\n" +
                "      M420.79,353.53c-.86,3.95-2.63,8.21-7.14,4.75-1.46-1.12-3-3.77-1.57-8.46,1.06-3.47,3.72-6.11,5.99-5.51,2.58,.68,3.6,5.15,2.72,9.22Z\n" +
                "      M403.16,355c-3.2,3.83-7.81,4.64-11.11,.66-2.84-3.42-1.75-9.83,1.96-13.17s7.64-3,10.61,.3,2.38,7.63-1.45,12.2Z\n" +
                "      M410.75,322.77c-1.51,1.42-4.39,1.88-5.76,.24-1.17-1.41-1.35-3.89,.2-5.28s4.57-1.89,5.8-.52,1.73,3.7-.24,5.56Z\n" +
                "      M427.63,334.36c-2.7,2.52-6.93,2.54-9.09-.09-1.86-2.26-1.17-6.99,1.57-9.45s8.25-3.75,10.21-1.58,.82,7.83-2.69,11.11Z\n" +
                "      M440.3,311.95c-2.24,2.09-5.69,2.17-7.41,.07-1.48-1.81-.85-5.66,1.42-7.7s6.79-3.17,8.35-1.43,.55,6.35-2.36,9.07Z\n" +
                "      M413.41,325.99c-3.2,3.83-8.41,4.35-11.47,1.87-3.45-2.79-3.84-9.73-.12-13.06s9.92-4.27,12.89-.97,2.54,7.59-1.29,12.16Z\n" +
                "      M426.36,299.38c-1.54,1.39-4.55,1.64-5.91,0-1.17-1.41-.22-4.01,1.33-5.4s3.79-1.41,5.02-.04,1.11,4.06-.43,5.45Z\n" +
                "      M428.47,302.69c-3.2,3.83-8.9,4.64-12.2,.66-2.84-3.42-2.38-8.57,1.33-11.91s9.34-4.24,12.31-.94,2.39,7.61-1.44,12.18Z\n" +
                "      M446.98,275.05c-1.54,1.39-4.28,1.5-5.65-.15-1.17-1.41-.48-3.87,1.06-5.26s4.05-1.06,5.28,.3,.85,3.71-.7,5.1Z\n" +
                "      M450.51,278.47c-3.2,3.83-10.65,4.42-13.95,.45-2.84-3.42-1.24-8.89,2.48-12.23s10.26-4.27,13.23-.97,2.08,8.17-1.75,12.74Z\n" +
                "      M456.91,292.99c-1.54,1.39-4.55,1.86-5.91,.22-1.17-1.41-.22-4.23,1.33-5.62s4.62-1.6,5.85-.24,.28,4.25-1.27,5.64Z\n" +
                "      M460.48,294.9c-3.2,3.83-8.77,5.79-12.07,1.81-2.84-3.42-3.42-9.89,.3-13.23s9.66-3.42,12.62-.12,2.99,6.96-.85,11.54Z\n" +
                "      M473.15,261.48c-1.71,1.17-5.81,1.56-7.18-.08-1.17-1.41-.63-4.33,.91-5.72s5.64-3.7,8.17-.52c1.14,1.43,1.33,4.11-1.91,6.32Z\n" +
                "      M477.07,263.05c-3.2,3.83-11.07,5.3-14.38,1.33-2.84-3.42-2.06-8.26,1.65-11.6s11.02-4.47,13.99-1.17,2.57,6.86-1.26,11.44Z\n" +
                "      M482.18,280.61c-1.54,1.39-3.56,2.76-6.52,.66-1.89-1.34-1.24-5.97,.32-7.34,2.34-2.05,5-.78,6.2,.6,1.61,1.85,1.54,4.69,0,6.08Z\n" +
                "      M485.64,283.38c-3.2,3.83-10.66,5.47-13.96,1.5-2.84-3.42-2.87-10.47,.84-13.8s10.88-3.84,13.85-.54,3.1,8.27-.73,12.84Z\n" +
                "      M503.67,253.95c-1.54,1.39-4.49,1.58-5.86-.06-1.17-1.41-.73-4.49,.81-5.88s4.18-1.17,5.41,.2,1.18,4.35-.36,5.74Z\n" +
                "      M508.26,255.58c-3.2,3.83-12.1,6.42-15.4,2.45-2.84-3.42-2.38-9.02,1.33-12.35s11.05-4.45,14.01-1.15,3.89,6.48,.06,11.05Z\n" +
                "      M513.75,275.97c-3.06,2.34-4.99,2.5-7.61-.16-2.83-2.88-.27-6.24,1.37-7.51,2.88-2.23,6.4-.71,7.46,.79,1.97,2.79,.44,5.63-1.22,6.89Z\n" +
                "      M518.95,277.73c-3.2,3.83-14.34,4.35-17.64,.37-2.84-3.42-2.67-9.55,1.05-12.89s12.63-2.84,16.11-.08c3.99,3.16,4.31,8.02,.48,12.59Z\n" +
                "      M533.08,251.95c-1.39,1.55-4.84,1.89-6.89,0-1.57-1.45-.95-5.02,.6-6.4,3.65-3.26,6.1-1.76,7.73,.12,1.2,1.39,.97,3.59-1.45,6.28Z\n" +
                "      M538.46,255.05c-3.2,3.83-12.83,4.28-16.13,.3-2.84-3.42-3.16-8.76,.55-12.1s12.56-4.02,15.52-.72,3.89,7.94,.06,12.52Z\n" +
                "      M421.1,365.37c-14.61,8.21-1.89,16.91,2.69,14.52\n" +
                "      M544.95,268.73c-10.54,1.29-5.56,12.12,.21,9.87\n" +
                "      M544.95,263.13c-18.51,1.61-13.64,24.48,0,20.21\n" +
                "\n" +
                "    M474.94,614.72c-1.54,1.39-5.32,1.2-6.68-.45-1.17-1.41-1.31-4.51,.24-5.9s4.98-1.19,6.21,.18,1.79,4.78,.24,6.16Z\n" +
                "    M479.06,617.8c-3.15,3.89-9.73,3.5-13.43-.91-3.18-3.79-3.68-9.34,.04-12.68s11.43-3.71,14.74-.03,2.42,8.97-1.35,13.61Z\n" +
                "    M521.53,657.05c-1.54,1.39-5.32,1.2-6.68-.45-1.17-1.41-1.31-4.51,.24-5.9s4.98-1.19,6.21,.18,1.79,4.78,.24,6.16Z\n" +
                "    M525.65,660.13c-3.15,3.89-9.73,3.5-13.43-.91-3.18-3.79-3.68-9.34,.04-12.68s11.43-3.71,14.74-.03,2.42,8.97-1.35,13.61Z\n" +
                "    M501.56,674.88c-1.97,.66-5.36-1.01-5.96-3.06-.52-1.76,.58-4.66,2.55-5.32s5.04,.88,5.63,2.62-.25,5.09-2.22,5.76Z\n" +
                "    M504.12,679.34c-4.43,2.32-10.32-.64-11.97-6.16-1.41-4.74,.33-10.03,5.06-11.63s11.96,1.13,13.54,5.82c1.58,4.69-1.33,9.19-6.63,11.97Z\n" +
                "    M482.07,685.16c-1.05,.94-3.61,.81-4.53-.3-.79-.96-.89-3.06,.16-4s3.38-.8,4.21,.12,1.21,3.24,.16,4.18Z\n" +
                "    M484.86,687.25c-2.14,2.63-6.59,2.37-9.1-.62-2.15-2.57-2.49-6.33,.02-8.59s7.75-2.51,9.99-.02,1.64,6.08-.91,9.23Z\n" +
                "    M494.61,596.21c-1.54,1.39-5.34,2.56-7.97,0-2.05-1.99-1.27-6.69,.28-8.08s5.04-2.25,7.21-.44c3.12,2.6,2.03,7.13,.48,8.52Z\n" +
                "    M498.65,598.02c-3.2,3.83-12.04,5.91-15.34,1.93-2.84-3.42-5.32-10.81,.3-15.89,3.7-3.35,13.1-2.88,16.07,.43s2.8,8.96-1.03,13.53Z\n" +
                "    M541.63,636.88c-1.54,1.39-5.34,2.56-7.97,0-2.05-1.99-1.27-6.69,.28-8.08s5.04-2.25,7.21-.44c3.12,2.6,2.03,7.13,.48,8.52Z\n" +
                "    M545.68,638.69c-3.2,3.83-12.04,5.91-15.34,1.93-2.84-3.42-5.32-10.81,.3-15.89,3.7-3.35,13.1-2.88,16.07,.43s2.8,8.96-1.03,13.53Z\n" +
                "    M450.78,631.43c11.43,.76,7.65-9.42,5.78-10.37\n" +
                "    M448.47,636.98c21.63,2.01,16.02-15.66,9.07-18.93\n" +
                "    M543.69,568.46c-1.54,1.39-3.8,1.22-5.16-.42-1.17-1.41-.97-3.6,.58-4.99s3.79-1.41,5.02-.04,1.11,4.06-.43,5.45Z\n" +
                "    M544.95,558.69c-22.26-2.26-10.9,21.42,0,14.34\n" +
                "    M505.78,566.59c5.16,5.4,13.45,.4,9.34-4.59\n" +
                "    M501.43,568.37c8.49,13.95,24.81-.87,16.4-7.42\n" +
                "    M106.37,659.3c-3.21,2.88-7.49,4.3-10.32,.89-2.44-2.93-2.01-7.47,1.2-10.35,3.21-2.88,7.88-2.92,10.42-.09,2.55,2.83,1.91,6.67-1.3,9.55Z\n" +
                "    M110.04,663.71c-5.12,6.11-13.1,7.18-18.37,.83-4.53-5.45-4.66-13.01,1.27-18.34,5.93-5.33,14.7-7.42,19.44-2.14,4.74,5.27,3.78,12.35-2.34,19.65Z\n" +
                "    M97.32,622.88c-3.21,2.88-7.88,2.54-10.72-.88-2.44-2.93-2.01-7.47,1.2-10.35,3.21-2.88,7.88-2.92,10.42-.09,2.55,2.83,2.31,8.44-.9,11.32Z\n" +
                "    M101.87,627.55c-5.12,6.11-14.38,5.14-19.66-1.2-4.53-5.45-4.84-13.77,1.09-19.1,5.93-5.33,14.88-6.66,19.62-1.39,4.74,5.27,5.06,14.38-1.05,21.68Z\n" +
                "    M128.72,620.15c-2,1.8-4.92,1.58-6.69-.55-1.52-1.83-1.26-4.66,.75-6.46s4.91-1.82,6.5-.06,1.44,5.26-.56,7.06Z\n" +
                "    M191.23,669.1c-1.69,1.52-4.2,1.28-5.75-.59-1.34-1.61-1.16-4.05,.52-5.56s4.19-1.49,5.59,.07,1.33,4.57-.36,6.08Z\n" +
                "    M133.64,626.1c-4.15,4.96-12.26,4.92-16.54-.23-3.68-4.43-3.93-11.18,.88-15.5,4.81-4.32,12.08-5.41,15.93-1.13,3.85,4.28,4.7,10.92-.27,16.85Z\n" +
                "    M151.52,630.99c-1.54,1.39-3.8,1.22-5.16-.42-1.17-1.41-.97-3.6,.58-4.99s3.79-1.41,5.02-.04,1.11,4.06-.43,5.45Z\n" +
                "    M155.32,635.58c-3.2,3.83-9.46,3.8-12.77-.17-2.84-3.42-3.03-8.62,.68-11.96s9.32-4.17,12.29-.87,3.62,8.43-.21,13Z\n" +
                "    M138.79,659.47c-.2,3.56-1.75,6.9-5.08,6.57-2.86-.28-5.03-3.18-4.83-6.74s2.69-6.32,5.56-6.16,4.55,2.77,4.35,6.33Z\n" +
                "    M144.55,661.34c-.53,7.01-5.88,11.81-13.16,9.88-6.26-1.66-10.39-7.08-8.84-13.93,1.55-6.85,6.93-12.79,13.25-11.36,6.32,1.43,9.38,7.04,8.75,15.42Z\n" +
                "    M15.41,646.6c14.57,3.27,14.21-14.16,10.73-14.95\n" +
                "    M11.55,650.86c21.88,8.25,25.53-19.11,18.12-23.8\n" +
                "    M80.25,582.77c10.45,14.02,22.87,1.29,20.44-5.8\n" +
                "    M117.67,580.82c-3.32,14.03,13.28,17.98,17.99,12.66\n" +
                "    M143.38,599.76c-5.48,8.62,4.85,14.5,11.11,11.15\n" +
                "    M73.83,584.46c13.94,25,37.32,5.19,33.1-8.21\n" +
                "    M38.49,616.07c16.1,6.11,14.73-7.17,10.39-9.54\n" +
                "    M35.22,619.82c24.59,11.55,27.27-9.72,18-16.67\n" +
                "    M173,637.88c-13.65,4.95,4.57,16.43,4.53,4.89\n" +
                "    M171.85,633.59c-24.41,3.36,.05,32.47,10.51,13.11\n" +
                "    M195.17,672.47c6.52-17.55-24.45-19.47-15.95,0\n" +
                "    M14.94,190.14c6.14,2.96,9.4-6.64,5.98-8.89\n" +
                " \n" +
                "      M470.99,151.6c1.09,2.71-2.5,6.42-5.16,7.38-2.29,.83-6.86,1.3-7.69-.79s1.03-5.06,3.13-6.3c3.3-1.96,8.85-2.47,9.72-.29Z\n" +
                "      M489.8,145.16c.7,2.16-.32,4.67-2.98,5.64-2.29,.83-6.56,1.49-7.87-1.42-.92-2.06,1.63-4.15,3.72-5.39,3.3-1.96,6.4-1.07,7.13,1.17Z\n" +
                "      M505.68,137.02c.7,2.16-.49,4.12-3.15,5.08-2.29,.83-4.42,1.09-5.26-1.01s.13-4.38,2.22-5.62c3.3-1.96,5.46-.69,6.19,1.54Z\n" +
                "      M519.75,127.62c.7,2.16-.03,4.96-2.69,5.93-2.29,.83-4.88,.24-5.72-1.85s.13-4.38,2.22-5.62c3.3-1.96,5.46-.69,6.19,1.54Z\n" +
                "      M535.7,113.26c.87,2.75-.31,6.42-4.11,7.81-3.27,1.2-6.27,2.06-7.96-2.08-1.08-2.66,.65-5.32,3.44-7.39,3.91-2.91,7.73-1.2,8.63,1.66Z\n" +
                "      M539.58,95.74c.38,1.22-.14,2.85-1.82,3.46-1.45,.53-2.78,.91-3.53-.92-.48-1.18,.29-2.36,1.52-3.28,1.73-1.29,3.43-.53,3.83,.73Z\n" +
                "      M537.53,78.13c.38,1.22-.14,2.85-1.82,3.46-1.45,.53-2.78,.91-3.53-.92-.48-1.18,.29-2.36,1.52-3.28,1.73-1.29,3.43-.53,3.83,.73Z\n" +
                "      M522.03,69.79c-.36,.93-1.48,1.67-2.81,1.22-1.15-.38-2.15-.8-1.72-2.28,.28-.95,1.33-1.3,2.54-1.25,1.7,.06,2.37,1.35,2,2.32Z\n" +
                "      M542.5,93.83c1.03,3.18-.04,7.32-3.97,8.75-3.38,1.23-7.21,.36-8.44-2.73s.19-6.46,3.28-8.29c4.88-2.89,8.06-1.03,9.14,2.27Z\n" +
                "      M540.05,77.75c1.04,3.15,.16,7.19-3.42,8.48-3.08,1.11-6.64,.15-7.87-2.92s-1.25-6.55,1.55-8.26c4.42-2.7,8.66-.57,9.74,2.7Z\n" +
                "      M524.81,67.69c.82,2.55,.98,5.9-2.23,7.07-2.76,1-8.42-.72-9.41-3.19s.2-5.19,2.73-6.68c4-2.35,8.06,.15,8.92,2.8Z\n" +
                "      M438.35,157.46c3.42,8.36,9.04-1.05,8.23-3.05\n" +
                "      M504.56,73.57c-.6-13.59-12.95-5.28-13.46-.24\n" +
                "      M486.15,74.15c-2.19-9.19-10.51-3.1-10.25,.34\n" +
                "      M433.84,159.59c6.71,14.28,17.76,.28,16.14-5.64\n" +
                "      M416.13,170.49c8.3,6.41,10.9-5.81,8.92-7.35\n" +
                "      M397.03,188.34c13.96,2.97,12.25-8.34,9.8-9.49\n" +
                "      M389.06,203.82c12.12-.55,7.34-9.66,5.02-10.08\n" +
                "      M413.1,172.36c11.92,11.96,19.02-5.19,15.29-10.55\n" +
                "  \n" +
                "    M60.46,193.45c-.91,.81-2.73,1.02-3.38,.22-.56-.68-.75-2.06,.15-2.87s2.88-.87,3.47-.22,.66,2.05-.24,2.87Z\n" +
                "    M62.92,196.46c-2.22,2.58-6.18,2.73-8.22,.25-1.75-2.13-2.23-5.84,.3-8.12s6.59-2.31,8.43-.26,2.13,5.04-.51,8.13Z\n" +
                "    M65.4,171.67c-1.14,1.02-3.45,1.26-4.28,.24-.72-.88-.98-2.63,.16-3.65s3.64-1.07,4.39-.23,.87,2.62-.27,3.64Z\n" +
                "    M66.76,173.56c-2.42,2.76-6.55,3.08-8.57,.61-1.74-2.12-2.07-5.93,.66-8.39s6.96-2.67,8.79-.63,2.01,5.11-.88,8.41Z\n" +
                "    M64.68,153.09c-1.06,.95-3.15,1.25-3.86,.36-.62-.76-.79-2.31,.27-3.27s3.31-1.09,3.97-.36,.69,2.31-.37,3.26Z\n" +
                "    M66.52,155.48c-2.23,2.6-6.23,2.74-8.29,.25-1.77-2.15-2.25-5.89,.3-8.18s6.64-2.33,8.5-.26,2.16,5.08-.51,8.19Z\n" +
                "    M60.11,133.06c-.84,.75-2.64,.83-3.34-.03-.61-.73-.9-2.14-.06-2.89s2.79-.67,3.43,.04,.81,2.12-.03,2.88Z\n" +
                "    M63.93,136.3c-2.28,2.75-6.67,2.64-9.11-.29-2.1-2.52-2.88-6.72-.23-9.11s7.15-2.14,9.34,.29,2.72,5.82,0,9.11Z\n" +
                "    M54.62,112.48c-1.06,.95-3.27,1.11-4.1,.1-.72-.87-1.02-2.57,.04-3.52s3.46-.92,4.21-.09,.92,2.56-.15,3.51Z\n" +
                "    M57.15,115.24c-2.65,3.07-7.34,3.29-9.72,.39-2.05-2.49-2.57-6.87,.45-9.58s7.82-2.8,9.97-.4,2.47,5.93-.7,9.59Z\n" +
                "    M46.79,92.54c-1.06,.95-3.27,1.11-4.1,.1-.72-.87-1.02-2.57,.04-3.52s3.46-.92,4.21-.09,.92,2.56-.15,3.51Z\n" +
                "    M49.8,95.49c-2.65,3.11-7.47,3.23-10,.18-2.17-2.62-2.81-7.16,.24-9.9s7.98-2.72,10.25-.18,2.68,6.19-.49,9.9Z\n" +
                "    M40.88,74.78c-1.36,1.23-4.36,1.26-5.57-.21-1.05-1.26-1.61-3.63-.25-4.85s4.63-.98,5.72,.23,1.46,3.6,.1,4.83Z\n" +
                "    M43.63,77.56c-2.45,3-7.32,2.74-10.11-.59-2.4-2.87-3.4-7.58-.53-10.17,2.87-2.58,7.87-2.18,10.37,.6s3.19,6.57,.27,10.15Z\n" +
                "    M36.64,51.48c-1.06,.95-3.27,1.11-4.1,.1-.72-.87-1.02-2.57,.04-3.52s3.46-.92,4.21-.09,.92,2.56-.15,3.51Z\n" +
                "    M39.21,53.9c-2.41,2.82-6.75,2.96-8.99,.25-1.93-2.34-2.46-6.4,.3-8.88,2.76-2.48,7.2-2.51,9.22-.26s2.35,5.53-.53,8.89Z\n" +
                "    M41.6,33.91c-1.06,.95-3.27,1.11-4.1,.1-.72-.87-1.02-2.57,.04-3.52s3.46-.92,4.21-.09,.92,2.56-.15,3.51Z\n" +
                "    M44.53,37.13c-2.7,3.1-7.38,3.41-9.7,.58-1.99-2.43-2.42-6.76,.64-9.51s7.85-2.94,9.95-.6,2.34,5.83-.89,9.53Z\n" +
                "    M71.35,10.86c-1.25,.41-3.14-.21-3.44-1.3-.26-.94,.14-2.4,1.39-2.81s3.22,.42,3.52,1.35-.22,2.36-1.47,2.77Z\n" +
                "    M72.54,14.42c-3.43,1.57-7.43,.25-8.27-2.8-.72-2.62,.59-6.23,4.19-7.41s7.64,.29,8.48,2.86-.31,5.46-4.41,7.34Z\n" +
                "    M90.57,8.05c-1.25,.41-3.14-.21-3.44-1.3-.26-.94,.14-2.4,1.39-2.81s3.22,.42,3.52,1.35-.22,2.36-1.47,2.77Z\n" +
                "    M91.76,11.62c-3.43,1.57-7.43,.25-8.27-2.8-.72-2.62,.59-6.23,4.19-7.41s7.64,.29,8.48,2.86-.31,5.46-4.41,7.34Z\n" +
                "    M109.68,10.91c-1.06-.34-2.05-1.7-1.72-2.6,.29-.77,1.29-1.58,2.34-1.24s2,1.89,1.75,2.67-1.32,1.51-2.38,1.17Z\n" +
                "    M108.73,13.95c-3.14-.62-5.22-3.51-4.29-6.01,.8-2.15,3.49-3.98,6.55-3.01s5.1,3.98,4.4,6.17-2.91,3.6-6.66,2.85Z\n" +
                "    M62.28,34.1c-.87,1.13-3.01,1.69-4.02,.84-.86-.73-1.48-2.34-.61-3.47s3.23-1.54,4.13-.85,1.37,2.35,.5,3.48Z\n" +
                "    M78.82,23.99c-1.06,.95-3.27,1.11-4.1,.1-.72-.87-1.02-2.57,.04-3.52s3.46-.92,4.21-.09,.92,2.56-.15,3.51Z\n" +
                "    M64.37,36.78c-2.8,3.18-7.54,3.59-9.84,.78-1.97-2.41-2.32-6.77,.83-9.61s8.01-3.12,10.09-.8,2.26,5.84-1.09,9.64Z\n" +
                "    M54.55,51.38c-.88,.79-2.72,.93-3.4,.09-.59-.72-.84-2.13,.04-2.92s2.87-.77,3.49-.08,.75,2.12-.13,2.91Z\n" +
                "    M55.92,52.85c-1.97,2.3-5.51,2.41-7.35,.18-1.58-1.92-2.03-5.24,.22-7.27s5.88-2.03,7.54-.18,1.94,4.53-.41,7.27Z\n" +
                "    M80.64,26.45c-2.24,2.59-8.04,2.85-10.06,.4-1.73-2.1-1.53-6.04,2.22-8.17,2.98-1.69,6.6-2.37,8.42-.34s2.09,5.01-.59,8.1Z\n" +
                "    M98.93,19.85c-2.09,2.34-4.94,2.99-6.52,1.04-1.36-1.67-1.98-3.85,.36-5.96s5.08-2.08,6.52-.48,2.14,2.6-.36,5.4Z\n" +
                "    M109.51,22.64c-1.42,1.58-3.34,2.04-4.38,.75-.9-1.11-1.3-2.56,.29-3.99s3.43-1.44,4.38-.38,1.41,1.72-.29,3.61Z\n" +
                "    M118.38,27.11c-2.39-.32-4.05-1.84-3.5-3.65,.47-1.56,1.58-2.88,3.94-2.3s3.54,2.31,3.15,3.89-.72,2.45-3.58,2.07Z\n" +
                "    M127.05,18.48c-1.42,1.96-3.54,2.69-4.88,1.35-1.15-1.15-1.79-2.76-.16-4.56s3.71-2.02,4.92-.92,1.81,1.8,.12,4.13Z\n" +
                "    M132.57,33.11c-1.42,1.96-3.82,1.58-5.16,.23-1.15-1.15-1.79-2.76-.16-4.56s3.71-2.02,4.92-.92,2.09,2.91,.4,5.25Z\n" +
                "    M139.2,29.4c-1.05,1.53-3.2,.96-4.52-.34-1.14-1.11-1.86-2.56-.62-3.94s3.06-1.35,4.24-.28,2.15,2.72,.9,4.55Z\n" +
                "    M145.19,45.58c-1.59,.96-3.39-.73-4.12-2.7-.63-1.69-.73-3.49,.99-4.2s3.41,.27,4.1,1.94,.94,3.8-.97,4.96Z\n" +
                "    M135.59,46.32c-2.07,1.2-4.24-.63-5.04-2.83-.69-1.9-.72-3.95,1.5-4.86s4.29,.11,5.06,1.97,.96,4.29-1.52,5.72Z\n" +
                "    M144.64,59.93c-2.07,1.2-3.18,.51-3.98-1.69-.69-1.9-1.48-4.35,.74-5.26s3.7,.07,4.47,1.93,1.25,3.58-1.23,5.01Z\n" +
                "    M133.88,59.81c-1.64,1.74-4.13,1.36-4.94-.85-.69-1.9-.76-4.59,1.46-5.5s4.07-.23,4.83,1.64,.5,2.75-1.35,4.71Z\n" +
                "    M137.74,73.4c-1.64,1.74-3.41,1.97-4.21-.24-.69-1.9-.76-4.59,1.46-5.5s4.07-.23,4.83,1.64-.22,2.14-2.08,4.1Z\n" +
                "    M128.56,72.03c-1.42,1.5-2.94,1.7-3.63-.2-.59-1.64-.65-3.96,1.26-4.75s3.51-.2,4.17,1.41-.19,1.84-1.79,3.54Z\n" +
                "    M125.5,83.07c-2.06,1.7-4.06,2.12-4.67,.35-.52-1.52-.25-3.81,2.35-4.88,2.59-1.06,4.57-.73,5.18,.76s-.53,1.85-2.86,3.77Z\n" +
                "    M106.44,84.57c-2.06,1.7-4.06,2.12-4.67,.35-.52-1.52-.25-3.81,2.35-4.88,2.59-1.06,4.57-.73,5.18,.76s-.53,1.85-2.86,3.77Z\n" +
                "    M96.52,80.22c-1.41,1.16-2.79,1.46-3.21,.24-.36-1.05-.17-2.62,1.61-3.35s3.14-.5,3.56,.52-.36,1.27-1.96,2.59Z\n" +
                "    M118.13,80.22c-1.41,1.16-2.79,1.46-3.21,.24-.36-1.05-.17-2.62,1.61-3.35s3.14-.5,3.56,.52-.36,1.27-1.96,2.59Z\n" +
                "    M87.85,72.26c-1.41,1.16-2.79,1.46-3.21,.24-.36-1.05-.17-2.62,1.61-3.35s3.14-.5,3.56,.52-.36,1.27-1.96,2.59Z\n" +
                "    M88.15,63.3c-1.21,1.37-2.56,1.52-3.22-.29-.57-1.55-.68-3.74,.99-4.42s3.1-.09,3.73,1.44-.12,1.73-1.49,3.28Z\n" +
                "    M91.71,54.12c-1.21,1.37-2.56,1.52-3.22-.29-.57-1.55-.68-3.74,.99-4.42s3.1-.09,3.73,1.44-.12,1.73-1.49,3.28Z\n" +
                "    M100.73,48.69c-1.52,1.29-3.02,1.59-3.49,.21-.41-1.19-.23-2.96,1.69-3.75,1.93-.79,3.41-.5,3.89,.66s-.37,1.43-2.09,2.88Z\n" +
                "    M108.91,47.29c-1.4,.35-2.45,.05-2.31-1.04,.13-.94,.82-2.07,2.32-1.95s2.35,.82,2.27,1.76-.71,.83-2.29,1.23Z\n" +
                "    M54.85,18.77c-1.06,.95-3.27,1.11-4.1,.1-.72-.87-1.02-2.57,.04-3.52s3.46-.92,4.21-.09,.92,2.56-.15,3.51Z\n" +
                "    M57.24,21.29c-2.24,2.59-7.52,2.33-9.54-.12-1.73-2.1-2.17-5.8,.38-8.09s10.09-1.47,11.91,.56-.08,4.55-2.75,7.65Z\n" +
                "    M73.71,121c-5.38,2.53-1.4,12.5,1.99,10.74\n" +
                "    M60.67,213.76c-7.13-21.1-26.93,9.79-4.82,8.47\n" +
                "    M51.13,227.66c-12.45-4.71-18.23,8.88-12.52,13.81\n" +
                "    M33.74,245.52c-8.09-2.78-17.15,4.27-14.26,10.27\n" +
                "    M11.75,260.89c-5.17-3.87-10.9-1.89-10.75,5.8\n" +
                "    M57.53,216.54c-6.4-12.56-8.94,3.5-2.38,.95\n" +
                "    M68.01,98.45c-6.44,2.67-1.67,13.19,2.38,11.33\n" +
                "    M61.16,79.65c-6.08,3.37-.11,13.05,3.7,10.75\n" +
                "    M57.25,61.06c-11.71,1.14-8.74,14.62,1.44,11.27\n" +
                "    M57.38,64.32c-5.34,.57-4.01,7.01,.64,5.39"
    ).toNodes()

    val head1 = PathParser().parsePathString(
        "M29.49,217.97c.11-.12,.19-.24,.27-.38,1.9-3.61,23.87-54.78,19.06-35.88C37.67,225.53,6.95,232.79,1.19,235.26V38.51S109.89-20.74,182.21,7.75c34.15,13.45,62.74,28.8,71.01,48.64,7.81,18.74,26.39,37.93,5.3,90.85s-41.38,73.93-29.79,113.8S10.4,189.68,48.82,181.71"
    ).toNodes()
    val head2 = PathParser().parsePathString(
        "M2.11,233.16L0,118.05s68,49.33,71.82,24.75c3.82-24.59,20.87,47.07-69.71,90.37Z"
    ).toNodes()
    val head3 = PathParser().parsePathString(
        "M182.36,62.99l84.85,58.37s-85.55,40.12-70.56,56.73c14.99,16.61-49.36-4.45-14.28-115.1Z"
    ).toNodes()
    val head4 = PathParser().parsePathString(
        "M174.8,19.41l2.2,205.27s-37.62,50.48-71.5,17.78C77.22,215.16,28.74,88.99,174.8,19.41Z"
    ).toNodes()
    val eyeWhite1 = PathParser().parsePathString(
        "M142.66,48.71c-1.3,18.88-7.14,46.71-24.12,46.71-18.25,0-26.17-23.83-25.33-47.74,.97-27.64,3.43-45.99,19.59-47.07,13.91-.93,31.79,19.97,29.86,48.1Z"
    ).toNodes()
    val eyeWhite2 = PathParser().parsePathString(
        "M60.69,68.88c-9.92,26.58-27.32,35.76-42.78,28.87C-3.69,88.13-3.11,65.14,5.83,40.01,17.2,8.05,34.18-4.08,49.72,1.19c22.47,7.63,19.39,45.13,10.97,67.69Z"
    ).toNodes()
    val eyeBlack1 = PathParser().parsePathString(
        "M46.72,12.1c-15.46-2.58-24.28,11.6-30.68,33.83-.02,.09-.05,.17-.07,.26l26.27,4.77-28.42,3.83c-3.95,18.64-2.39,34.51,9.88,36.87,13.99,2.69,24.91-11.99,30.76-34.37,6.26-23.99,5.14-43.03-7.73-45.18Z"
    ).toNodes()
    val eyeBlack2 = PathParser().parsePathString(
        "M116,9.22c-15.83,0-15.43,17.21-14.73,38.06l19.82-.72-19.53,7.13c1.18,18.9,5.45,33.09,17.04,33.09,8.83,0,14.47-17.42,16.14-38.78,2.01-25.79-9.89-38.78-18.73-38.78Z"
    ).toNodes()
}


@Language("AGSL")
val WOBBLE_SHADER = """
    uniform float2 iResolution;
    uniform float iTime;
    uniform shader contents;

    vec4 main(in vec2 fragCoord) {
    	vec2 uv = fragCoord.xy / iResolution.xy * 0.8 + 0.1;

        uv += sin(iTime * vec2(1.0, 2.0) + uv* 2.0) * 0.01;

        return contents.eval(uv * iResolution.xy);
    }
""".trimIndent()


@Language("AGSL")
val PERLIN_NOISE = """
    uniform float2 resolution;
    uniform float time;
    uniform shader contents;

    //
    // Description : Array and textureless GLSL 2D/3D/4D simplex
    //               noise functions.
    //      Author : Ian McEwan, Ashima Arts.
    //  Maintainer : stegu
    //     Lastmod : 20201014 (stegu)
    //     License : Copyright (C) 2011 Ashima Arts. All rights reserved.
    //               Distributed under the MIT License. See LICENSE file.
    //               https://github.com/ashima/webgl-noise
    //               https://github.com/stegu/webgl-noise
    //

    vec3 mod289(vec3 x) {
      return x - floor(x * (1.0 / 289.0)) * 289.0;
    }

    vec4 mod289(vec4 x) {
      return x - floor(x * (1.0 / 289.0)) * 289.0;
    }

    vec4 permute(vec4 x) {
         return mod289(((x*34.0)+10.0)*x);
    }

    float snoise(vec3 v)
    {
      const vec2  C = vec2(1.0/6.0, 1.0/3.0) ;
      const vec4  D = vec4(0.0, 0.5, 1.0, 2.0);

       // First corner
      vec3 i  = floor(v + dot(v, C.yyy) );
      vec3 x0 =   v - i + dot(i, C.xxx) ;

      // Other corners
      vec3 g = step(x0.yzx, x0.xyz);
      vec3 l = 1.0 - g;
      vec3 i1 = min( g.xyz, l.zxy );
      vec3 i2 = max( g.xyz, l.zxy );

      //   x0 = x0 - 0.0 + 0.0 * C.xxx;
      //   x1 = x0 - i1  + 1.0 * C.xxx;
      //   x2 = x0 - i2  + 2.0 * C.xxx;
      //   x3 = x0 - 1.0 + 3.0 * C.xxx;
      vec3 x1 = x0 - i1 + C.xxx;
      vec3 x2 = x0 - i2 + C.yyy; // 2.0*C.x = 1/3 = C.y
      vec3 x3 = x0 - D.yyy;      // -1.0+3.0*C.x = -0.5 = -D.y

      // Permutations
      i = mod289(i);
      vec4 p = permute( permute( permute(
                 i.z + vec4(0.0, i1.z, i2.z, 1.0 ))
               + i.y + vec4(0.0, i1.y, i2.y, 1.0 ))
               + i.x + vec4(0.0, i1.x, i2.x, 1.0 ));

      // Gradients: 7x7 points over a square, mapped onto an octahedron.
      // The ring size 17*17 = 289 is close to a multiple of 49 (49*6 = 294)
      float n_ = 0.142857142857; // 1.0/7.0
      vec3  ns = n_ * D.wyz - D.xzx;

      vec4 j = p - 49.0 * floor(p * ns.z * ns.z);  //  mod(p,7*7)

      vec4 x_ = floor(j * ns.z);
      vec4 y_ = floor(j - 7.0 * x_ );    // mod(j,N)

      vec4 x = x_ *ns.x + ns.yyyy;
      vec4 y = y_ *ns.x + ns.yyyy;
      vec4 h = 1.0 - abs(x) - abs(y);

      vec4 b0 = vec4( x.xy, y.xy );
      vec4 b1 = vec4( x.zw, y.zw );

      vec4 s0 = floor(b0)*2.0 + 1.0;
      vec4 s1 = floor(b1)*2.0 + 1.0;
      vec4 sh = -step(h, vec4(0.0));

      vec4 a0 = b0.xzyw + s0.xzyw*sh.xxyy ;
      vec4 a1 = b1.xzyw + s1.xzyw*sh.zzww ;

      vec3 p0 = vec3(a0.xy,h.x);
      vec3 p1 = vec3(a0.zw,h.y);
      vec3 p2 = vec3(a1.xy,h.z);
      vec3 p3 = vec3(a1.zw,h.w);

      //Normalise gradients
      vec4 norm = inversesqrt(vec4(dot(p0,p0), dot(p1,p1), dot(p2, p2), dot(p3,p3)));
      p0 *= norm.x;
      p1 *= norm.y;
      p2 *= norm.z;
      p3 *= norm.w;

      // Mix final noise value
      vec4 m = max(0.5 - vec4(dot(x0,x0), dot(x1,x1), dot(x2,x2), dot(x3,x3)), 0.0);
      m = m * m;
      return 105.0 * dot( m*m, vec4( dot(p0,x0), dot(p1,x1),
                                    dot(p2,x2), dot(p3,x3) ) );
    }

    half4 main(in vec2 fragCoord) {
        vec2 uv = (fragCoord.xy / resolution.xy);

        float noise = snoise(vec3(uv.x * 6, uv.y * 6, time * 0.5));

        noise *= exp(-length(abs(uv * 1.5)));
        vec2 offset1 = vec2(noise * 0.02);
        vec2 offset2 = vec2(0.02) / resolution.xy;
        uv += offset1 - offset2;

        return contents.eval(uv * resolution.xy);
    }
""".trimIndent()