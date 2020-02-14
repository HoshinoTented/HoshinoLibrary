plugins {
	kotlin("jvm") version "1.3.61"
}

repositories {
	maven("https://maven.aliyun.com/repository/public")
	jcenter()
}

dependencies {
	compile(kotlin("stdlib", "1.3.61"))
}