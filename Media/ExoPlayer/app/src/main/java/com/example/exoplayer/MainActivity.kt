package com.example.exoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {
    companion object {
        const val URL_VIDEO =
            "https://dl211.dlmate53.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyZ0tuY0p4NUJVdEZiNWV2OFlhMi9DeUlzUk9JT3dqeUkybkg5b2Z3VGJkZk5YQUJCMkF0STB5Unppbjl0Z3B1Q3pXdUt3dlFJSTBjMEM5d3JEbXRHRXpyRk93WElDSVRPNFRTQ0ErZ1JRbWkzYlc2YUtSNFVlOWtpdm9naDdHTkhkUGt4bE9HS2FWcWN3TmpUR09TcVNoaThOQWpIL2F4OGtNZ3FQWTdTU3o1dUY3NEk4d2ZCY3hWc1FZKzdtd2h0U0pnQjljb2JoWisyN3Y0cWVEVWRzVVJlQzhXSEpWYUdkUnVxck5EMTQ3bW1BOHFDbm5wK29BdXlVcktPVlh2Q21SMTlXaVZRcTZUZjJpRjhHVVpPV3Q4b1AvNi9Wa2dGSEFzdXVhMmNobWp3ZXlYTjM5UTVSYWwwSXo5ZWJSdzgxemtIQ0oxUTR1b0xWWi9SdXZXbk5wRnNkYmJYb2ZjNG9RQWlGRmd1bWs3TDRzOUkxSmVrWHU1dUZnSWFKdS9jK2ZsKzlEZ1R5SDl1QTVPQW5HNml6ZEdwejNSbUFpNEdFRURXcUVRaHdDekJ3cVowQzQ%3D"
        const val URL_AUDIO =
            "https://dl258.dlmate68.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyZ0ptZHcwakNRcUZhZEo2SUEvaEtLbUtzVktFYmNPMllML1ZJeHk3REhOWmNXSk5VcmJrSlUyRVRiVDBkZDQ2eG5NM3RrWFNzTmtVVC91cnFpdWhtSXdqZ0w2SS9IcFU3MU9mWDVwcGxScTNpUE9uYWVNNmtXcDVpWC84QXVSYVNJMXNUTU9QdjZOb05sYjNIUE9NcU8yZ3NkVDlHQ005TWROMktRPQ%3D%3D"
    }

    private var player: ExoPlayer? = null

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    private fun init() {
        val videoItem = MediaItem.fromUri(URL_VIDEO)
        val audioItem = MediaItem.fromUri(URL_AUDIO)

        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            viewBinding.videoView.player = exoPlayer
//            exoPlayer.setMediaItem(videoItem)
            exoPlayer.setMediaItem(audioItem)
            exoPlayer.prepare()
        }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            init()
        }
    }

    public override fun onResume() {
        super.onResume()
//        hideSystemUI()
        if (Util.SDK_INT <= 23 && player == null) {
            init()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}