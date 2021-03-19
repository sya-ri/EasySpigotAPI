import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("org.jetbrains.dokka") version "1.4.30"
    `maven-publish`
    signing
}

repositories {
    jcenter()
}

group = "com.github.sya-ri.spigot.api"
version = "2.2.1"

bukkit {
    name = "EasySpigotAPI"
    version = project.version.toString()
    description = "A library for easy use of the Spigot API."
    main = "com.github.syari.spigot.api.EasySpigotAPI"
    author = "sya_ri"
    website = "https://github.com/sya-ri/EasySpigotAPI"
    apiVersion = "1.16"
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("EasySpigotAPI")
}

val sourceJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    repositories {
        maven {
            url = uri(
                if (version.toString().endsWith("SNAPSHOT")) {
                    "https://oss.sonatype.org/content/repositories/snapshots"
                } else {
                    "https://oss.sonatype.org/service/local/staging/deploy/maven2"
                }
            )
            credentials {
                username = System.getenv("SONATYPE_USER")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
    publications {
        register<MavenPublication>("maven") {
            groupId = "com.github.sya-ri"
            artifactId = "EasySpigotAPI"
            from(components["kotlin"])
            artifact(sourceJar.get())
            pom {
                packaging = "pom"
                name.set("EasySpigotAPI")
                description.set("A library for easy use of the Spigot API.")
                url.set("https://github.com/sya-ri/EasySpigotAPI")
                licenses {
                    license {
                        name.set("Eclipse Public License 2.0")
                        url.set("https://www.eclipse.org/legal/epl-2.0/")
                    }
                }
                developers {
                    developer {
                        id.set("sya-ri")
                        name.set("sya-ri")
                        email.set("sya79lua@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/sya-ri/EasySpigotAPI.git")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}
