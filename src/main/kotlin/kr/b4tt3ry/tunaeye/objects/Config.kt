package kr.b4tt3ry.tunaeye.objects

data class Config(
    val url: String = "TunaEye_API_URL",
    val secretKey: String = "SECRET_KEY"
): JSONConvertable