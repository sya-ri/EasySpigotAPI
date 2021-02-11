import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

tasks.withType<Jar> {
    enabled = false
}

subprojects {
    apply(plugin = "net.minecrell.plugin-yml.bukkit")

    dependencies {
        implementation(project(":api"))
    }

    tasks.withType<ShadowJar> {
        archiveBaseName.set("EasySpigotAPI-Sample-${project.name}")
    }

    configure<BukkitPluginDescription> {
        name = "EasySpigotAPI-Sample-${project.name}"
        version = project(":api").version.toString()
        description = "EasySpigotAPI Sample: ${project.name}"
        main = "com.github.syari.spigot.api.sample.${project.name.replace('-', '.')}.Main"
        author = "sya_ri"
        website = "https://github.com/sya-ri/EasySpigotAPI"
        apiVersion = "1.16"
        depend = listOf("EasySpigotAPI")
    }
}
