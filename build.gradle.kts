// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("vkid.manifest.placeholders") version "1.1.0"
}

// читаем секреты (из local.properties)
val localProps = java.util.Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}
val vkClientId: String = requireNotNull(localProps.getProperty("VKIDClientID")) {
    "Missing vkClientId in local.properties"
}
val vkClientSecret: String = requireNotNull(localProps.getProperty("VKIDClientSecret")) {
    "Missing vkClientSecret in local.properties"
}


vkidManifestPlaceholders {
    vkidRedirectHost = "vk.ru"
    vkidRedirectScheme = "vk$vkClientId"
    vkidClientId = vkClientId
    vkidClientSecret = vkClientSecret
}
