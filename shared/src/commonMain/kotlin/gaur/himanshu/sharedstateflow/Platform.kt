package gaur.himanshu.sharedstateflow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform