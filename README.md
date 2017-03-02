# 重拾后端之Spring Boot（一）：REST API的搭建可以这样简单

标签（空格分隔）： 未分类

---
![Spring Boot][1]

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

![使用Spring Initializr进行工程的生成][2]

如果你使用Intellij IDEA进行开发，里面也集成了这个工具，大家可以自行尝试。

![Intellij IDEA中集成了 Spring Initializr ][3]

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
// 如果遇到下载速度慢的话可以换成阿里镜像
// maven {url "http://maven.aliyun.com/nexus/content/repositories/central/"}
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
    // 如果遇到下载速度慢的话可以换成阿里镜像
    // maven {url "http://maven.aliyun.com/nexus/content/repositories/central/"}
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

![Gradle工具窗口][4]

点击左上角的刷新按钮可以将所有依赖下载类库下来。注意IDEA有时提示是否要配置wrapper使用带源码的gradle包。

![提示使用带源码的gradle以便有API的文档][5]

如果遇到不知道什么原因导致一直刷新完成不了的情况，请在项目属性中选择 `Use local gradle distribution`

![image_1b77vqast1d2h1qioeepsdm1tkb9.png-192.5kB][6]

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

![IDE中右键启动应用][7]
 
 启动后，打开浏览器访问 `http://localhost:8080/todos` 就可以看到我们的json形式的返回结果了。
 
![浏览器直接访问一下的效果][8]

## 配置Spring Beans工具

由于使用的是Spring框架，Spring工具窗口也是需要的，一般来说如果你安装了Spring插件的话，IDEA会自动探测到你的项目是基于Spring的。一般在你增加了Applicaiton入口后，会提示是否添加context。

![检测到Spring配置，但提示尚未关联][9]

遇到这种情况，请点提示框的右方的下箭头展开提示。

![展开后的提示框][10]
 
点击 `Create Default Context` 会将目前的所有没有map的Spring配置文件都放在这个默认配置的上下文中。在Spring的工具窗口中可以看到下图效果。

![创建Spring配置的默认上下文][11]
 
本章代码：https://github.com/wpcfan/spring-boot-tut/tree/chap01

# 重拾后端之Spring Boot（二）：MongoDB的无缝集成

上一节，我们做的那个例子有点太简单了，通常的后台都会涉及一些数据库的操作，然后在暴露的API中提供处理后的数据给客户端使用。那么这一节我们要做的是集成MongoDB （ https://www.mongodb.com ）。

## MongoDB是什么？

MongoDB是一个NoSQL数据库，是NoSQL中的一个分支：文档数据库。和传统的关系型数据库比如Oracle、SQLServer和MySQL等有很大的不同。传统的关系型数据库（RDBMS）已经成为数据库的代名词超过20多年了。对于大多数开发者来说，关系型数据库是比较好理解的，表这种结构和SQL这种标准化查询语言毕竟是很大一部分开发者已有的技能。那么为什么又搞出来了这个什么劳什子NoSQL，而且看上去NoSQL数据库正在飞快的占领市场。

### NoSQL的应用场景是什么？

假设说我们现在要构建一个论坛，用户可以发布帖子（帖子内容包括文本、视频、音频和图片等）。那么我们可以画出一个下图的表关系结构。

![论坛的简略ER图][12]

这种情况下我们想一下这样一个帖子的结构怎么在页面中显示，如果我们希望显示帖子的文字，以及关联的图片、音频、视频、用户评论、赞和用户的信息的话，我们需要关联八个表取得自己想要的数据。如果我们有这样的帖子列表，而且是随着用户的滚动动态加载，同时需要监听是否有新内容的产生。这样一个任务我们需要太多这种复杂的查询了。

NoSQL解决这类问题的思路是，干脆抛弃传统的表结构，你不是帖子有一个结构关系吗，那我就直接存储和传输一个这样的数据给你，像下面那样。

```json
{
    "id":"5894a12f-dae1-5ab0-5761-1371ba4f703e",
    "title":"2017年的Spring发展方向",
    "date":"2017-01-21",
    "body":"这篇文章主要探讨如何利用Spring Boot集成NoSQL",
    "createdBy":User,
    "images":["http://dev.local/myfirstimage.png","http://dev.local/mysecondimage.png"],
    "videos":[
        {"url":"http://dev.local/myfirstvideo.mp4", "title":"The first video"},
        {"url":"http://dev.local/mysecondvideo.mp4", "title":"The second video"}
    ],
    "audios":[
        {"url":"http://dev.local/myfirstaudio.mp3", "title":"The first audio"},
        {"url":"http://dev.local/mysecondaudio.mp3", "title":"The second audio"}
    ]
}

```

NoSQL一般情况下是没有Schema这个概念的，这也给开发带来较大的自由度。因为在关系型数据库中，一旦Schema确定，以后更改Schema，维护Schema是很麻烦的一件事。但反过来说Schema对于维护数据的完整性是非常必要的。

一般来说，如果你在做一个Web、物联网等类型的项目，你应该考虑使用NoSQL。如果你要面对的是一个对数据的完整性、事务处理等有严格要求的环境（比如财务系统），你应该考虑关系型数据库。

## 在Spring中集成MongoDB

在我们刚刚的项目中集成MongoDB简单到令人发指，只有三个步骤：

 1. 在 `build.gradle` 中更改 `compile('org.springframework.boot:spring-boot-starter-web')` 为 `compile("org.springframework.boot:spring-boot-starter-data-rest")`
 2. 在 `Todo.java` 中给 `private String id;` 之前加一个元数据修饰 `@Id` 以便让Spring知道这个Id就是数据库中的Id
 2. 新建一个如下的 `TodoRepository.java`

```java
package dev.local.todo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRepository extends MongoRepository<Todo, String>{
}
```

此时我们甚至不需要Controller了，所以暂时注释掉 `TodoController.java` 中的代码。然后我们 `./gradlew bootRun` 启动应用。访问 `http://localhost:8080/todo` 我们会得到下面的的结果。

```json
{
    _embedded: {
        todo: [ ]
    },
    _links: {
        self: {
            href: "http://localhost:8080/todo"
        },
        profile: {
            href: "http://localhost:8080/profile/todo"
        }
    },
    page: {
        size: 20,
        totalElements: 0,
        totalPages: 0,
        number: 0
    }
}
```

我勒个去，不光是有数据集的返回结果 `todo: [ ]` ，还附赠了一个links对象和page对象。如果你了解 `Hypermedia` 的概念，就会发现这是个符合 `Hypermedia` REST API返回的数据。

说两句关于 `MongoRepository<Todo, String>` 这个接口，前一个参数类型是领域对象类型，后一个指定该领域对象的Id类型。 

### Hypermedia REST

简单说两句Hypermedia是什么。简单来说它是可以让客户端清晰的知道自己可以做什么，而无需依赖服务器端指示你做什么。原理呢，也很简单，通过返回的结果中包括不仅是数据本身，也包括指向相关资源的链接。拿上面的例子来说（虽然这种默认状态生成的东西不是很有代表性）：links中有一个profiles，我们看看这个profile的链接 `http://localhost:8080/profile/todo` 执行的结果是什么：

```json
{
  "alps" : {
    "version" : "1.0",
    "descriptors" : [ 
        {
          "id" : "todo-representation",
          "href" : "http://localhost:8080/profile/todo",
          "descriptors" : [ 
              {
                "name" : "desc",
                "type" : "SEMANTIC"
              }, 
              {
                "name" : "completed",
                "type" : "SEMANTIC"
              } 
          ]
        }, 
        {
          "id" : "create-todo",
          "name" : "todo",
          "type" : "UNSAFE",
          "rt" : "#todo-representation"
        }, 
        {
          "id" : "get-todo",
          "name" : "todo",
          "type" : "SAFE",
          "rt" : "#todo-representation",
          "descriptors" : [ 
              {
                "name" : "page",
                "doc" : {
                  "value" : "The page to return.",
                  "format" : "TEXT"
                },
                "type" : "SEMANTIC"
              }, 
              {
                "name" : "size",
                "doc" : {
                  "value" : "The size of the page to return.",
                  "format" : "TEXT"
                },
                "type" : "SEMANTIC"
              }, 
              {
                "name" : "sort",
                "doc" : {
                  "value" : "The sorting criteria to use to calculate the content of the page.",
                  "format" : "TEXT"
                },
                "type" : "SEMANTIC"
              } 
            ]
        }, 
        {
          "id" : "patch-todo",
          "name" : "todo",
          "type" : "UNSAFE",
          "rt" : "#todo-representation"
        }, 
        {
          "id" : "update-todo",
          "name" : "todo",
          "type" : "IDEMPOTENT",
          "rt" : "#todo-representation"
        }, 
        {
          "id" : "delete-todo",
          "name" : "todo",
          "type" : "IDEMPOTENT",
          "rt" : "#todo-representation"
        }, 
        {
          "id" : "get-todo",
          "name" : "todo",
          "type" : "SAFE",
          "rt" : "#todo-representation"
        } 
    ]
  }
}
```

这个对象虽然我们暂时不是完全的理解，但大致可以猜出来，这个是todo这个REST API的元数据描述，告诉我们这个API中定义了哪些操作和接受哪些参数等等。我们可以看到todo这个API有增删改查等对应功能。

其实呢，Spring是使用了一个叫 `ALPS` （http://alps.io/spec/index.html） 的专门描述应用语义的数据格式。摘出下面这一小段来分析一下，这个描述了一个get方法，类型是 `SAFE` 表明这个操作不会对系统状态产生影响（因为只是查询），而且这个操作返回的结果格式定义在 `todo-representation` 中了。 `todo-representation`

```json
{
  "id" : "get-todo",
  "name" : "todo",
  "type" : "SAFE",
  "rt" : "#todo-representation"
} 
```

还是不太理解？没关系，我们再来做一个实验，启动 PostMan （不知道的同学，可以去Chrome应用商店中搜索下载）。我们用Postman构建一个POST请求：

![用Postman构建一个POST请求添加一个Todo][13]
 
执行后的结果如下，我们可以看到返回的links中包括了刚刚新增的Todo的link `http://localhost:8080/todo/588a01abc5d0e23873d4c1b8` （ `588a01abc5d0e23873d4c1b8` 就是数据库自动为这个Todo生成的Id），这样客户端可以方便的知道指向刚刚生成的Todo的API链接。

![执行添加Todo后的返回Json数据][14]

再举一个现实一些的例子，我们在开发一个“我的”页面时，一般情况下除了取得我的某些信息之外，因为在这个页面还会有一些可以链接到更具体信息的页面链接。如果客户端在取得比较概要信息的同时就得到这些详情的链接，那么客户端的开发就比较简单了，而且也更灵活了。

其实这个描述中还告诉我们一些分页的信息，比如每页20条记录(`size: 20`)、总共几页（`totalPages：1`）、总共多少个元素（`totalElements: 1`）、当前第几页（`number: 0`）。当然你也可以在发送API请求时，指定page、size或sort参数。比如 `http://localhost:8080/todos?page=0&size=10` 就是指定每页10条，当前页是第一页（从0开始）。

### 魔法的背后

这么简单就生成一个有数据库支持的REST API，这件事看起来比较魔幻，但一般这么魔幻的事情总感觉不太托底，除非我们知道背后的原理是什么。首先再来回顾一下 `TodoRepository` 的代码：

```java
@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRepository extends MongoRepository<Todo, String>{
}
```

Spring是最早的几个IoC（控制反转或者叫DI）框架之一，所以最擅长的就是依赖的注入了。这里我们写了一个Interface，可以猜到Spring一定是有一个这个接口的实现在运行时注入了进去。如果我们去 `spring-data-mongodb`  的源码中看一下就知道是怎么回事了，这里只举一个小例子，大家可以去看一下 `SimpleMongoRepository.java` （ [源码链接][15] ），由于源码太长，我只截取一部分：

```java
public class SimpleMongoRepository<T, ID extends Serializable> implements MongoRepository<T, ID> {

	private final MongoOperations mongoOperations;
	private final MongoEntityInformation<T, ID> entityInformation;

	/**
	 * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and {@link MongoTemplate}.
	 * 
	 * @param metadata must not be {@literal null}.
	 * @param mongoOperations must not be {@literal null}.
	 */
	public SimpleMongoRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {

		Assert.notNull(mongoOperations);
		Assert.notNull(metadata);

		this.entityInformation = metadata;
		this.mongoOperations = mongoOperations;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	public <S extends T> S save(S entity) {

		Assert.notNull(entity, "Entity must not be null!");

		if (entityInformation.isNew(entity)) {
			mongoOperations.insert(entity, entityInformation.getCollectionName());
		} else {
			mongoOperations.save(entity, entityInformation.getCollectionName());
		}

		return entity;
	}
    ...
	public T findOne(ID id) {
		Assert.notNull(id, "The given id must not be null!");
		return mongoOperations.findById(id, entityInformation.getJavaType(), entityInformation.getCollectionName());
	}

	private Query getIdQuery(Object id) {
		return new Query(getIdCriteria(id));
	}

	private Criteria getIdCriteria(Object id) {
		return where(entityInformation.getIdAttribute()).is(id);
	}
	...
}
```

也就是说其实在运行时Spring将这个类或者其他具体接口的实现类注入了应用。这个类中有支持各种数据库的操作。我了解到这步就觉得ok了，有兴趣的同学可以继续深入研究。

虽然不想在具体类上继续研究，但我们还是应该多了解一些关于 `MongoRepository` 的东西。这个接口继承了 `PagingAndSortingRepository` （定义了排序和分页） 和 `QueryByExampleExecutor`。而 `PagingAndSortingRepository` 又继承了 `CrudRepository` （定义了增删改查等）。

第二个魔法就是 `@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")` 这个元数据的修饰了，它直接对MongoDB中的集合（本例中的todo）映射到了一个REST URI（todo）。因此我们连Controller都没写就把API搞出来了，而且还是个Hypermedia REST。

其实呢，这个第二个魔法只在你需要变更映射路径时需要。本例中如果我们不加 `@RepositoryRestResource` 这个修饰符的话，同样也可以生成API，只不过其路径按照默认的方式变成了 `todoes` ，大家可以试试把这个元数据修饰去掉，然后重启服务，访问 `http://localhost:8080/todoes` 看看。

说到这里，顺便说一下REST的一些约定俗成的规矩。一般来说如果我们定义了一个领域对象 （比如我们这里的Todo），那么这个对象的集合（比如Todo的列表）可以使用这个对象的命名的复数方式定义其资源URL，也就是刚刚我们访问的 `http://localhost:8080/todoes`，对于新增一个对象的操作也是这个URL，但Request的方法是POST。

而这个某个指定的对象（比如指定了某个ID的Todo）可以使用 `todoes/:id` 来访问，比如本例中 `http://localhost:8080/todoes/588a01abc5d0e23873d4c1b8`。对于这个对象的修改和删除使用的也是这个URL，只不过HTTP Request的方法变成了PUT（或者PATCH）和DELETE。

这个里面默认采用的这个命名 `todoes` 是根据英语的语法来的，一般来说复数是加s即可，但这个todo，是辅音+o结尾，所以采用的加es方式。 `todo` 其实并不是一个真正意义上的单词，所以我认为更合理的命名方式应该是 `todos`。所以我们还是改成 `@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")`

## 无招胜有招

刚才我们提到的都是开箱即用的一些方法，你可能会想，这些东西看上去很炫，但没有毛用，实际开发过程中，我们要使用的肯定不是这么简单的增删改查啊。说的有道理，我们来试试看非默认方法。那么我们就来增加一个需求，我们可以通过查询Todo的描述中的关键字来搜索符合的项目。

显然这个查询不是默认的操作，那么这个需求在Spring Boot中怎么实现呢？非常简单，只需在 `TodoRepository` 中添加一个方法：

```java
...
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByDescLike(@Param("desc") String desc);
}
```

太不可思议了，这样就行？不信可以启动服务后，在浏览器中输入 `http://localhost:8080/todos/search/findByDescLike?desc=swim`  去看看结果。是的，我们甚至都没有写这个方法的实现就已经完成了该需求（题外话，其实 `http://localhost:8080/todos?desc=swim` 这个URL也起作用）。

你说这里肯定有鬼，我同意。那么我们试试把这个方法改个名字 `findDescLike` ，果然不好用了。为什么呢？这套神奇的疗法的背后还是那个我们在第一篇时提到的 `Convention over configuration`，要神奇的疗效就得遵循Spring的配方。这个配方就是方法的命名是有讲究的：Spring提供了一套可以通过命名规则进行查询构建的机制。这套机制会把方法名首先过滤一些关键字，比如 `find…By`, `read…By`, `query…By`, `count…By` 和 `get…By` 。系统会根据关键字将命名解析成2个子语句，第一个 `By` 是区分这两个子语句的关键词。这个 `By` 之前的子语句是查询子语句（指明返回要查询的对象），后面的部分是条件子语句。如果直接就是 `findBy…` 返回的就是定义Respository时指定的领域对象集合（本例中的Todo组成的集合）。

一般到这里，有的同学可能会问 `find…By`, `read…By`, `query…By`,  `get…By` 到底有什么区别啊？答案是。。。木有区别，就是别名，从下面的定义可以看到这几个东东其实生成的查询是一样的，这种让你不用查文档都可以写对的方式也比较贴近目前流行的自然语言描述风格（类似各种DSL）。

```java
private static final String QUERY_PATTERN = "find|read|get|query|stream";
```

刚刚我们实验了模糊查询，那如果要是精确查找怎么做呢，比如我们要筛选出已完成或未完成的Todo，也很简单：

```java
  List<Todo> findByCompleted(@Param("completed") boolean completed);
```

### 嵌套对象的查询怎么搞？

看到这里你会问，这都是简单类型，如果复杂类型怎么办？嗯，好的，我们还是增加一个需求看一下：现在需求是要这个API是多用户的，每个用户看到的Todo都是他们自己创建的项目。我们新建一个User领域对象：

```java
package dev.local.user;

import org.springframework.data.annotation.Id;

public class User {
    @Id private String id;
    private String username;
    private String email;
    //此处为节省篇幅省略属性的getter和setter
}

```

为了可以添加User数据，我们需要一个User的REST API，所以添加一个 `UserRepository`

```java
package dev.local.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

```

然后给 `Todo` 领域对象添加一个User属性。

```java
package dev.local.todo;
//省略import部分
public class Todo {
    //省略其他部分
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

```

接下来就可以来把 `TodoRepository` 添加一个方法定义了，我们先实验一个简单点的，根据用户的email来筛选出这个用户的Todo列表：

```java
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByUserEmail(@Param("userEmail") String userEmail);
}
```

现在需要构造一些数据了，你可以通过刚刚我们建立的API使用Postman工具来构造：我们这里创建了2个用户，以及一些Todo项目，分别属于这两个用户，而且有部分项目的描述是一样的。接下来就可以实验一下了，我们在浏览器中输入 `http://localhost:8080/todos/search/findByUserEmail?userEmail=peng@gmail.com` ，我们会发现返回的结果中只有这个用户的Todo项目。

```
{
  "_embedded" : {
    "todos" : [ {
      "desc" : "go swimming",
      "completed" : false,
      "user" : {
        "username" : "peng",
        "email" : "peng@gmail.com"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/todos/58908a92c5d0e2524e24545a"
        },
        "todo" : {
          "href" : "http://localhost:8080/todos/58908a92c5d0e2524e24545a"
        }
      }
    }, {
      "desc" : "go for walk",
      "completed" : false,
      "user" : {
        "username" : "peng",
        "email" : "peng@gmail.com"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/todos/58908aa1c5d0e2524e24545b"
        },
        "todo" : {
          "href" : "http://localhost:8080/todos/58908aa1c5d0e2524e24545b"
        }
      }
    }, {
      "desc" : "have lunch",
      "completed" : false,
      "user" : {
        "username" : "peng",
        "email" : "peng@gmail.com"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/todos/58908ab6c5d0e2524e24545c"
        },
        "todo" : {
          "href" : "http://localhost:8080/todos/58908ab6c5d0e2524e24545c"
        }
      }
    }, {
      "desc" : "have dinner",
      "completed" : false,
      "user" : {
        "username" : "peng",
        "email" : "peng@gmail.com"
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/todos/58908abdc5d0e2524e24545d"
        },
        "todo" : {
          "href" : "http://localhost:8080/todos/58908abdc5d0e2524e24545d"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/todos/search/findByUserEmail?userEmail=peng@gmail.com"
    }
  }
}
```

看到结果后我们来分析这个 `findByUserEmail` 是如何解析的：首先在 `By` 之后，解析器会按照 `camel` （每个单词首字母大写）的规则来分词。那么第一个词是 `User`，这个属性在 `Todo` 中有没有呢？有的，但是这个属性是另一个对象类型，所以紧跟着这个词的 `Email` 就要在 `User` 类中去查找是否有 `Email` 这个属性。聪明如你，肯定会想到，那如果在 `Todo` 类中如果还有一个属性叫 `userEmail` 怎么办？是的，这种情况下 `userEmail` 会被优先匹配，此时请使用 `_` 来显性分词处理这种混淆。也就是说如果我们的 `Todo` 类中同时有 `user` 和 `userEmail` 两个属性的情况下，我们如果想要指定的是 `user` 的 `email` ，那么需要写成 `findByUser_Email`。

还有一个问题，我估计很多同学现在已经在想了，那就是我们的这个例子中并没有使用 `user` 的 `id`，这不科学啊。是的，之所以没有在上面使用 `findByUserId` 是因为要引出一个易错的地方，下面我们来试试看，将 `TodoRepository` 的方法改成

```java
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByUserId(@Param("userId") String userId);
}

```

你如果打开浏览器输入 `http://localhost:8080/todos/search/findByUserId?userId=589089c3c5d0e2524e245458` (这里的Id请改成你自己mongodb中的user的id)，你会发现返回的结果是个空数组。原因是虽然我们在类中标识 `id` 为 `String` 类型，但对于这种数据库自己生成维护的字段，它在MongoDB中的类型是ObjectId，所以在我们的接口定义的查询函数中应该标识这个参数是 `ObjectId`。那么我们只需要改动 `userId` 的类型为 `org.bson.types.ObjectId` 即可。

```java
package dev.local.todo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByUserId(@Param("userId") ObjectId userId);
}
```

### 再复杂一些行不行？

好吧，到现在我估计还有一大波攻城狮表示不服，实际开发中需要的查询比上面的要复杂的多，再复杂一些怎么办？还是用例子来说话吧，那么现在我们想要模糊搜索指定用户的Todo中描述的关键字，返回匹配的集合。这个需求我们只需改动一行，这个以命名规则为基础的查询条件是可以加 `And`、`Or` 这种关联多个条件的关键字的。

```java
List<Todo> findByUserIdAndDescLike(@Param("userId") ObjectId userId, @Param("desc") String desc);
```

当然，还有其他操作符：`Between` (值在两者之间), `LessThan` (小于), `GreaterThan` （大于）, `Like` （包含）, `IgnoreCase` （b忽略大小写）, `AllIgnoreCase` （对于多个参数全部忽略大小写）, `OrderBy` （引导排序子语句）, `Asc` （升序，仅在 `OrderBy` 后有效） 和 `Desc` （降序，仅在 `OrderBy` 后有效）。

刚刚我们谈到的都是对于查询条件子语句的构建，其实在 `By` 之前，对于要查询的对象也可以有限定的修饰词 `Distinct` （去重，如有重复取一个值）。比如有可能返回的结果有重复的记录，可以使用 `findDistinctTodoByUserIdAndDescLike`。

我可以直接写查询语句吗？几乎所有码农都会问的问题。当然可以咯，也是同样简单，就是给方法加上一个元数据修饰符 `@Query`

```java
public interface TodoRepository extends MongoRepository<Todo, String>{
    @Query("{ 'user._id': ?0, 'desc': { '$regex': ?1} }")
    List<Todo> searchTodos(@Param("userId") ObjectId userId, @Param("desc") String desc);
}
```

采用这种方式我们就不用按照命名规则起方法名了，可以直接使用MongoDB的查询进行。上面的例子中有几个地方需要说明一下

 1.  `?0` 和 `?1` 是参数的占位符，`?0` 表示第一个参数，也就是 `userId`；`?1` 表示第二个参数也就是 `desc`。
 2.  使用`user._id` 而不是 `user.id` 是因为所有被 `@Id` 修饰的属性在Spring Data中都会被转换成 `_id`
 3.  MongoDB中没有关系型数据库的Like关键字，需要以正则表达式的方式达成类似的功能。
 
其实，这种支持的力度已经可以让我们写出相对较复杂的查询了。但肯定还是不够的，对于开发人员来讲，如果不给可以自定义的方式基本没人会用的，因为总有这样那样的原因会导致我们希望能完全掌控我们的查询或存储过程。但这个话题展开感觉就内容更多了，后面再讲吧。

本章代码：https://github.com/wpcfan/spring-boot-tut/tree/chap02

# 找回熟悉的Controller，Service

## Controller哪儿去了？

对于很多习惯了Spring开发的同学来讲，`Controller`，`Service`，`DAO` 这些套路突然间都没了会有不适感。其实呢，这些东西还在，只不过对于较简单的情景下，这些都变成了系统背后帮你做的事情。这一小节我们就先来看看如何将Controller再召唤回来。召唤回来的好处有哪些呢？首先我们可以自定义API URL的路径，其次可以对参数和返回的json结构做一定的处理。

如果要让 `TodoController` 可以和 `TodoRepository` 配合工作的话，我们当然需要在 `TodoController` 中需要引用 `TodoRepository`。

```java
public class TodoController {
    @Autowired
    private TodoRepository repository;
    //省略其它部分
}
```

`@Autowired` 这个修饰符是用于做依赖性注入的，上面的用法叫做 `field injection`，直接做类成员的注入。但Spring现在鼓励用构造函数来做注入，所以，我们来看看构造函数的注入方法：

```java
public class TodoController {

    private TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository){
        this.repository = repository;
    }
    //省略其它部分
}
```

当然我们为了可以让Spring知道这是一个支持REST API的 `Controller` ，还是需要标记其为 `@RestController`。由于默认的路径映射会在资源根用复数形式，由于todo是辅音后的o结尾，按英语习惯，会映射成 `todoes`。但这里用 `todos` 比 `todoes` 更舒服一些，所以我们再使用另一个 `@RequestMapping("/todos")` 来自定义路径。这个 `Controller` 中的其它方法比较简单，就是利用repository中的方法去增删改查即可。

```java
package dev.local.todo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoRepository repository;

    @Autowired
    public TodoController(TodoRepository repository){
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> getAllTodos(@RequestHeader(value = "userId") String userId) {
        return repository.findByUserId(new ObjectId(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    Todo addTodo(@RequestBody Todo addedTodo) {
        return repository.insert(addedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Todo getTodo(@PathVariable String id) {
        return repository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Todo updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
        updatedTodo.setId(id);
        return repository.save(updatedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Todo removeTodo(@PathVariable String id) {
        Todo deletedTodo = repository.findOne(id);
        repository.delete(id);
        return deletedTodo;
    }
}

```

上面的代码中需要再说明几个要点：

 1. 为什么在类上标记 `@RequestMapping("/todos")` 后在每个方法上还需要添加 `@RequestMapping`？类上面定义的 `@RequestMapping` 的参数会默认应用于所有方法，但如果我们发现某个方法需要有自己的特殊值时，就需要定义这个方法的映射参数。比如上面例子中 `addTodo`，路径也是 `todos`，但要求 Request的方法是 `POST`，所以我们给出了 `@RequestMapping(method = RequestMethod.POST)`。但 `getTodo` 方法的路径应该是 `todos/:id`，这时我们要给出 `@RequestMapping(value = "/{id}", method = RequestMethod.GET)`
 2. 这些方法接受的参数也使用了各种修饰符，`@PathVariable` 表示参数是从路径中得来的，而 `@RequestBody` 表示参数应该从 Http Request的`body` 中解析，类似的 `@RequestHeader` 表示参数是 Http Request的Header中定义的。

在可以测试之前，我们还需要使用 `@Repository` 来标记 `TodoRepository`，以便于Spring可以在依赖注入时可以找到这个类。

```java
package dev.local.todo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by wangpeng on 2017/1/26.
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{
    List<Todo> findByUserId(ObjectId userId);
}

```

接下来就可以用PostMan做一下测试：

![测试一下Controller][12]
 
## Service呢？在哪里？

熟悉Spring的童鞋肯定会问，我们刚才的做法等于直接是Controller访问Data了，隔离不够啊。其实我觉得有很多时候，这种简单设计是挺好的，因为业务还没有到达那步，过于复杂的设计其实没啥太大意义。但这里我们还是一步步来实践一下，找回大家熟悉的感觉。

回到原来的熟悉模式再简单不过的，新建一个 `TodoService` 接口，定义一下目前的增删改查几个操作：

```java
public interface TodoService {
    Todo addTodo(Todo todo);
    Todo deleteTodo(String id);
    List<Todo> findAll(String userId);
    Todo findById(String id);
    Todo update(Todo todo);
}
```

为预防我们以后使用 `MySQL` 等潜在的 “可扩展性”，我们给这个接口的实现命名为 `MongoTodoServiceImpl`，然后把 `Controller` 中的大部分代码拿过来改改就行了。当然为了系统可以找到这个依赖并注入需要的类中，我们标记它为 `@Service`

```java
@Service
public class MongoTodoServiceImpl implements TodoService{
    private final TodoRepository repository;

    @Autowired
    MongoTodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo addTodo(Todo todo) {
        return repository.insert(todo);
    }

    @Override
    public Todo deleteTodo(String id) {
        Todo deletedTodo = repository.findOne(id);
        repository.delete(id);
        return deletedTodo;
    }

    @Override
    public List<Todo> findAll(String userId) {
        return repository.findByUserId(new ObjectId(userId));
    }

    @Override
    public Todo findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Todo update(Todo todo) {
        repository.save(todo);
        return todo;
    }
}
```

最后把Controller中的所有方法改为使用Service的简单调用就大功告成了。

```
public class TodoController {

    private TodoService service;

    @Autowired
    public TodoController(TodoService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> getAllTodos(@RequestHeader(value = "userId") String userId) {
        return service.findAll(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    Todo addTodo(@RequestBody Todo addedTodo) {
        return service.addTodo(addedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Todo getTodo(@PathVariable String id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Todo updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
        updatedTodo.setId(id);
        return service.update(updatedTodo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Todo removeTodo(@PathVariable String id) {
        return service.deleteTodo(id);
    }
}
```

说实话如果每个简单类都这么写，我深深地赶脚背离了Spring Boot的意图，虽然你能举出1000个理由这么做有好处。类似的，DAO或DTO要写起来也很简单，但我还是建议在业务没有复杂之前还是享受Spring Boot带给我们的便利吧。

  [1]: http://static.zybuluo.com/wpcfan/xx9vcwko6cnqlpcds9gkre83/image_1b783isnc14631jfr14groi5fuo1g.png
  [2]: http://static.zybuluo.com/wpcfan/de99jylqsulfydybajqnw6nc/image_1b7081mu6vke127v1ej2jhp1b1i9.png
  [3]: http://static.zybuluo.com/wpcfan/1k8jb3uyfab7f42cljyjitjj/image_1b7089410gi4u3o1r1pdt2t4om.png
  [4]: http://static.zybuluo.com/wpcfan/a8jzrxuyv7lz6cnigke3k1dp/image_1b778ebofol0d7ijcb1a5pamj13.png
  [5]: http://static.zybuluo.com/wpcfan/219ydnaga0jczzx76drzpd2o/image_1b77gr2om1rb7u8111du137e1hoe1t.png
  [6]: http://static.zybuluo.com/wpcfan/z0n134l61e9ofg64phcfjagj/image_1b77vqast1d2h1qioeepsdm1tkb9.png
  [7]: http://static.zybuluo.com/wpcfan/gd06q598sdaf1v848668hzvr/image_1b782evj1iuuupb2e71fr51ngm13.png
  [8]: http://static.zybuluo.com/wpcfan/aqezgb1pkk85651fpbyote7n/image_1b780non09ng12vr67ult7ml2m.png
  [9]: http://static.zybuluo.com/wpcfan/si7egzd0jh5369qe2ghzyn7y/image_1b77h6kies66199e1spn1vs0kin2a.png
  [10]: http://static.zybuluo.com/wpcfan/d7d20ngovrg3a9jzc441om19/image_1b77h90ud140r1ghc1p7qeq515542n.png
  [11]: http://static.zybuluo.com/wpcfan/xkapgi54m6944yzc5zkor2bf/image_1b77hol5adff1et81prknrn59s34.png
  [12]: http://static.zybuluo.com/wpcfan/nxmplqhi4732w6xy6160dtjk/image_1ba756i081dkm1jbgnm91q72fctc.png