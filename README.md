# 重拾后端之Spring Boot（一）：REST API的搭建可以这样简单

标签（空格分隔）： 未分类

---

话说我当年接触Spring的时候着实兴奋了好一阵，IoC的概念当初第一次听说，感觉有种开天眼的感觉。记得当时的web框架和如今的前端框架的局面差不多啊，都是群雄纷争。但一晃好多年没写过后端，代码这东西最怕手生，所以当作重新学习了，顺便写个学习笔记。

## Spring Boot是什么？

还恍惚记得当初写Spring的时候要配置好多xml（在当时还是相对先进的模式），虽然实现了松耦合，但这些xml却又成为了项目甩不掉的负担 -- 随着项目越做越大，这些xml的可读性和可维护性极差。后来受.Net平台中Annotation的启发，Java世界中也引入了元数据的修饰符，Spring也可以使用这种方式进行配置。到了近些年，随着Ruby on Rails的兴起而流行开的 `Convention over configuration` 理念开始深入人心。那什么是 `Convention over configuration` 呢？简单来说就是牺牲一部分的自由度来减少配置的复杂度，打个比方就是如果你如果遵从我定义的一系列规则（打个比方，文件目录结构必须是blablabla的样子，文件命名必须是nahnahnah 的样子），那么你要配置的东西就非常简单甚至可以零配置。既然已经做到这个地步了，各种脚手架项目就纷纷涌现了，目的只有一个：让你更专注在代码的编写，而不是浪费在各种配置上。这两年前端也有类似趋势，各种前端框架的官方CLI纷纷登场：create-react-app，angular-cli，vue-cli等等。

那么Spring Boot就是Spring框架的脚手架了，它可以帮你快速搭建、发布一个Spring应用。官网列出了Spring Boot的几个主要目标

 - 提供一种快速和广泛适用的Spring开发体验
 - 开箱即用却又可以适应各种变化
 - 提供一系列开发中常用的“非功能性”的特性（比如嵌入式服务器、安全、度量、自检及外部配置等）
 - 不生成任何代码，不需要xml配置

## 安装Spring Boot

官方推荐的方式是通过sdkman（ http://sdkman.io/install.html ）来进行安装，当然这是对 `*nix` 而言。题外话，如果你使用的是Windows 10，真心希望大家安装Windows 10的Linux子系统，微软官方出品、原生支持，比虚拟机不知道快到那里去了 具体安装过程可以参考 https://linux.cn/article-7209-1.html 。安装 `sdkman` 的步骤非常简单，就两步：

 1. 打开一个terminal，输入 `curl -s "https://get.sdkman.io" | bash`
 2. 安装结束后，重启terminal，输入 `source "$HOME/.sdkman/bin/sdkman-init.sh"`

可以在terminal中验证一下是否安装成功 `sdk version`，如果你看到了版本号就是安装好了。

接下来，就可以安装Spring Boot了，还是打开terminal输入 `sdk install springboot`就ok了。

当然其实Mac的童鞋可以省略掉之前的sdkman安装直接使用 `brew` 安装，也是两步：

 1. 在terminal中输入 `brew tap pivotal/tap`
 2. 然后 `brew install springboot`

验证的话可以输入 `spring --version` 看看是否正常输出了版本号。

## 创建一个工程

有很多种方法可以创建一个Spring Boot项目，其中最简单的一种是通过一个叫Spring Initializr的在线工具 `http://start.spring.io/` 进行工程的生成。如下图所示，只需填写一些参数就可以生成一个工程包了。

![使用Spring Initializr进行工程的生成][1]

如果你使用Intellij IDEA进行开发，里面也集成了这个工具，大家可以自行尝试。

![Intellij IDEA中集成了 Spring Initializr ][2]

但下面我们要做的不是通过这种方式，而是手动的通过命令行方式创建。创建的是gradle工程，而不是maven的，原因呢是因为个人现在对于xml类型的配置文件比较无感;-)，官方推荐使用gradle 2.14.1版本，请自行安装gradle。下面来建立一个gradle工程，其实步骤也不算太难：

 1. 新建一个工程目录 `mkdir todo`
 2. 在此目录下使用gradle进行初始化 `gradle init`（就和在node中使用 `npm init` 的效果类似）

这个命令帮我们建立一个一个使用gradle进行管理的模版工程：

 - `build.gradle`：有过Android开发经验的童鞋可能觉得很亲切的，这个就是我们用于管理和配置工程的核心文件了。
 - `gradlew`：用于 `*nix` 环境下的gradle wrapper文件。
 - `gradlew.bat`：用于 `Windows` 环境下的gradle wrapper文件
 - `setting.gradle`：用于管理多项目的gradle工程时使用，单项目时可以不做理会。
 - gradle目录：wrapper的jar和属性设置文件所在的文件夹。

简单说两句什么是 `gradle wrapper`。你是否有过这样的经历？在安装/编译一个工程时需要一些先决条件，需要安装一些软件或设置一些参数。如果这一切比较顺利还好，但很多时候我们会发现这样那样的问题，比如版本不对，参数没设置等等。`gradle wrapper` 就是这样一个让你不会浪费时间在配置问题上的方案。它会对应一个开发中使用的gradle版本，以确保任何人任何时候得到的结果是一致的。

 - `./gradlew <task>`:  在 `*nix` 平台上运行，例如Linux或Mac OS X
 - `gradlew <task>` 在Windows平台运行（是通过`gradlew.bat`来执行的）

更多关于wrapper的知识可以去 https://docs.gradle.org/current/userguide/gradle_wrapper.html  查看。

那么下面我们打开默认生成的 `build.gradle` 文件，将其改造成下面的样子：

```gradle
/*
 * 这个build文件是由Gradle的 `init` 任务生成的。
 *
 * 更多关于在Gradle中构建Java项目的信息可以查看Gradle用户文档中的
 * Java项目快速启动章节
 * https://docs.gradle.org/3.3/userguide/tutorial_java_projects.html
 */
// 在这个段落中你可以声明你的build脚本需要的依赖和解析下载该依赖所使用的仓储位置
buildscript {
	ext {
		springBootVersion = '1.4.3.RELEASE'
	}
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
	}
}
/*
 * 在这个段落中你可以声明使用哪些插件
 * apply plugin: 'java' 代表这是一个Java项目，需要使用java插件
 * 如果想生成一个 `Intellij IDEA` 的工程，类似的如果要生成 
 * eclipse工程，就写 apply plugin: 'eclipse'
 * 同样的我们要学的是Spring Boot，所以应用Spring Boot插件
 */
apply plugin: 'java' 
apply plugin: 'idea' 
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'

// 在这个段落中你可以声明编译后的Jar文件信息
jar {
	baseName = 'todo'
	version = '0.0.1-SNAPSHOT'
}

// 在这个段落中你可以声明在哪里可以找到你的项目依赖
repositories {
    // 使用 'jcenter' 作为中心仓储查询解析你的项目依赖。
    // 你可以声明任何 Maven/Ivy/file 类型的依赖类库仓储位置
    mavenCentral()
}

// 在这个段落中你可以声明源文件和目标编译后的Java版本兼容性
sourceCompatibility = 1.8
targetCompatibility = 1.8

// 在这个段落你可以声明你的项目的开发和测试所需的依赖类库
dependencies {
    compile('org.springframework.boot:spring-boot-starter-web')
	testCompile('org.springframework.boot:spring-boot-starter-test')
}
```

首先脚本依赖中的 `spring-boot-gradle-plugin` 有什么作用呢？它提供了以下几个功能：

 1. 简化执行和发布：它可以把所有classpath的类库构建成一个单独的可执行jar文件，这样可以简化你的执行和发布等操作。
 2. 自动搜索入口文件：它会扫描 `public static void main()` 函数并且标记这个函数的宿主类为可执行入口。
 3. 简化依赖：一个典型的Spring应用还是需要很多依赖类库的，想要配置正确这些依赖挺麻烦的，所以这个插件提供了内建的依赖解析器会自动匹配和当前Spring Boot版本匹配的依赖库版本。

在最后一个段落中，我们看到我们的项目依赖两个类库，一个是 `spring-boot-starter-web` ，另一个是 `spring-boot-starter-test`。Spring Boot提供了一系列依赖类库的“模版”，这些“模版”封装了很多依赖类库，可以让我们非常方便的引用自己想实现的功能所需要的类库。如果我们去看看这个 `spring-boot-starter-web` 中究竟引用了什么，我们可以看看它的artifact文件（到 http://search.maven.org/ 可以查看）：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starters</artifactId>
		<version>1.4.3.RELEASE</version>
	</parent>
	<artifactId>spring-boot-starter-web</artifactId>
	<name>Spring Boot Web Starter</name>
	<description>Starter for building web, including RESTful, applications using Spring
		MVC. Uses Tomcat as the default embedded container</description>
	<url>http://projects.spring.io/spring-boot/</url>
	<organization>
		<name>Pivotal Software, Inc.</name>
		<url>http://www.spring.io</url>
	</organization>
	<properties>
		<main.basedir>${basedir}/../..</main.basedir>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
		</dependency>
	</dependencies>
</project>

```

## IDE支持

一般做Java开发，大部分团队还是喜欢用一个IDE，虽然我还是更偏爱文本编辑器类型的（比如sublime，vscode，atom等）。但是如果非挑一个重型IDE的话，我更喜欢Intellij IDEA。

使用IDEA的import project功能选中 `build.gradle`，将工程导入。由于是个gradle工程，请把 `View->Tools Window->Gradle` 的视图窗口调出来。

![Gradle工具窗口][3]

点击左上角的刷新按钮可以将所有依赖下载类库下来。注意IDEA有时提示是否要配置wrapper使用带源码的gradle包。

![提示使用带源码的gradle以便有API的文档][4]

如果遇到不知道什么原因导致一直刷新完成不了的情况，请在项目属性中选择 `Use local gradle distribution`

![image_1b77vqast1d2h1qioeepsdm1tkb9.png-192.5kB][5]

## 第一个Web API

### 领域对象

那么我们的源代码目录在哪里呢？我们得手动建立一个，这个目录一般情况下是 `src/main/java`。好的，下面我们要开始第一个RESTful的API搭建了，首先还是在 `src/main/java` 下新建一个 `package`。既然是本机的就叫 `dev.local` 吧。我们还是来尝试建立一个 Todo 的Web API，在 `dev.local` 下建立一个子 `package`: `todo`，然后创建一个Todo的领域对象：

```java
package dev.local.todo;

/**
 * Todo是一个领域对象（domain object）
 * Created by wangpeng on 2017/1/24.
 */
public class Todo {
    private String id;
    private String desc;
    private boolean completed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

```

这个对象很简单，只是描述了todo的几个属性： `id` 、 `desc` 和 `completed` 。我们的API返回或接受的参数就是以这个对象为模型的类或集合。

### 构造Controller

我们经常看到的RESTful API是这样的：`http://local.dev/todos`、`http://local.dev/todos/1` 。Controller就是要暴露这样的API给外部使用。现在我们同样的在 `todo` 下建立一个叫 `TodoController` 的java文件

```java
package dev.local.todo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用@RestController来标记这个类是个Controller
 */
@RestController
public class TodoController {
    // 使用@RequstMapping指定可以访问的URL路径
    @RequestMapping("/todos")
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        Todo item1 = new Todo();
        item1.setId("1");
        item1.setCompleted(false);
        item1.setDesc("go swimming");
        todos.add(item1);
        Todo item2 = new Todo();
        item2.setId("1");
        item2.setCompleted(true);
        item2.setDesc("go for lunch");
        todos.add(item2);
        return todos;
    }
}

```

上面这个文件也比较简单，但注意到以下几个事情：

 - `@RestController` 和 `@RequestMapping` 这两个是元数据注释，原来在.Net中很常见，后来Java也引进过来。一方面它们可以增加代码的可读性，另一方面也有效减少了代码的编写。具体机理就不讲了，简单来说就是利用Java的反射机制和IoC模式结合把注释的特性或属性注入到被注释的对象中。
 - 我们看到 `List<Todo> getAllTodos()` 方法中简单的返回了一个List，并未做任何转换成json对象的处理，这个是Spring会自动利用 ` Jackson` 这个类库的方法将其转换成了json。

我们到这就基本接近成功了，但是现在缺少一个入口，那么在 `dev.local` 包下面建立一个 `Applicaiton.java` 吧。

```java
package dev.local;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用的入口文件
 * Created by wangpeng on 2017/1/24.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

同样的我们只需标注这个类是 `@SpringBootApplication` 就万事大吉了。可以使用下面任意一种方法去启动我们的Web服务：

 1. 命令行中： `./gradlew bootRun`
 2. IDEA中在 `Application` 中右键选择 `Run 'Application'`

![IDE中右键启动应用][6]
 
 启动后，打开浏览器访问 `http://localhost:8080/todos` 就可以看到我们的json形式的返回结果了。
 
![浏览器直接访问一下的效果][7]

## 配置Spring Beans工具

由于使用的是Spring框架，Spring工具窗口也是需要的，一般来说如果你安装了Spring插件的话，IDEA会自动探测到你的项目是基于Spring的。一般在你增加了Applicaiton入口后，会提示是否添加context。

![检测到Spring配置，但提示尚未关联][8]

遇到这种情况，请点提示框的右方的下箭头展开提示。

![展开后的提示框][9]
 
点击 `Create Default Context` 会将目前的所有没有map的Spring配置文件都放在这个默认配置的上下文中。在Spring的工具窗口中可以看到下图效果。

![创建Spring配置的默认上下文][10]
 
本章代码：https://github.com/wpcfan/spring-boot-tut/tree/chap01

  [1]: http://static.zybuluo.com/wpcfan/de99jylqsulfydybajqnw6nc/image_1b7081mu6vke127v1ej2jhp1b1i9.png
  [2]: http://static.zybuluo.com/wpcfan/1k8jb3uyfab7f42cljyjitjj/image_1b7089410gi4u3o1r1pdt2t4om.png
  [3]: http://static.zybuluo.com/wpcfan/a8jzrxuyv7lz6cnigke3k1dp/image_1b778ebofol0d7ijcb1a5pamj13.png
  [4]: http://static.zybuluo.com/wpcfan/219ydnaga0jczzx76drzpd2o/image_1b77gr2om1rb7u8111du137e1hoe1t.png
  [5]: http://static.zybuluo.com/wpcfan/z0n134l61e9ofg64phcfjagj/image_1b77vqast1d2h1qioeepsdm1tkb9.png
  [6]: http://static.zybuluo.com/wpcfan/gd06q598sdaf1v848668hzvr/image_1b782evj1iuuupb2e71fr51ngm13.png
  [7]: http://static.zybuluo.com/wpcfan/aqezgb1pkk85651fpbyote7n/image_1b780non09ng12vr67ult7ml2m.png
  [8]: http://static.zybuluo.com/wpcfan/si7egzd0jh5369qe2ghzyn7y/image_1b77h6kies66199e1spn1vs0kin2a.png
  [9]: http://static.zybuluo.com/wpcfan/d7d20ngovrg3a9jzc441om19/image_1b77h90ud140r1ghc1p7qeq515542n.png
  [10]: http://static.zybuluo.com/wpcfan/xkapgi54m6944yzc5zkor2bf/image_1b77hol5adff1et81prknrn59s34.png