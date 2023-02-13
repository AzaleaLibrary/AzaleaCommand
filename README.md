<div>
  <a href="https://github.com/AzaleaLibrary/AzaleaCommand/actions/workflows/ci.yml">
    <img alt="Plugin CI" src="https://github.com/AzaleaLibrary/AzaleaCommand/actions/workflows/ci.yml/badge.svg?branch=master" />
  </a>
    <a href="https://github.com/AzaleaLibrary/AzaleaCommand/actions/workflows/cd.yml">
    <img alt="Plugin CD" src="https://github.com/AzaleaLibrary/AzaleaCommand/actions/workflows/cd.yml/badge.svg?branch=production" />
  </a>
  <a href="https://azalealibrary.net/#/releases/net/azalealibrary/configuration">
    <img src="https://azalealibrary.net/api/badge/latest/releases/net/azalealibrary/configuration?color=40c14a&name=Azalea%20Configuration&prefix=v" />
  </a>
</div>

# AzaleaCommand

## How to

### Import `AzaleaCommand`

In **pom.xml**:

```xml
    <repositories>
        <!-- other repositories -->
        <repository>
            <id>azalea-repo-releases</id>
            <name>Azalea Repository</name>
            <url>https://azalealibrary.net/releases</url>
        </repository>
    </repositories>
    
    <dependencies>
        <!-- other dependencies -->
        <dependency>
            <groupId>net.azalealibrary</groupId>
            <artifactId>command</artifactId>
            <version>1.0</version>
        </dependency>
    </dependencies>
```

### Usage

TODO