# migrbird
android dev summery.


```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

or

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```


[![](https://jitpack.io/v/nangonghuang/migrbird.svg)](https://jitpack.io/#nangonghuang/migrbird)
```groovy
dependencies {
    implementation 'com.github.nangonghuang:migrbird:Tag'
}
```