val projectName = "EasySpigotAPI"

group = "com.github.sya-ri.spigot.api"
version = "1.0.0-SNAPSHOT"

bukkit {
    name = projectName
    version = project.version.toString()
    description = "A library for easy use of the Spigot API."
    main = "com.github.syari.spigot.api.EasySpigotAPI"
    author = "sya_ri"
    website = "https://github.com/sya-ri/EasySpigotAPI"
    apiVersion = "1.16"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set(projectName)
}
