import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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
}
