## 背景
    为后续集成化测试测试准备，先搭建mock服务器，在集成化单元测试中，开发联调经常需要一个轻量级的maven mock server plugin

## 需求
-  系统间解耦，能够代理依赖系统（esb、eureka）请求,进行模拟返回；
-  前后端联调需要，解决由于前置条件不到位（刚定义的接口契约要马上就可以访问）导致开发资源互相等等；
-  集成化测试需要，解决无法清理依赖系统资源

## 须知
   分享mock不是目的，主要是为了解决代码重构方面的问题，具体可以看我的[博客](https://blog.csdn.net/alex_xfboy/article/details/85006654)
   
## 使用说明
```xml
  <plugin>
      <groupId>com.github.middleware</groupId>
      <artifactId>mock-plugin-maven</artifactId>
      <version>1.0-SNAPSHOT</version>
      <configuration>
          <port>8088</port>
          <configFile>${build.testSourceDirectory}\mock\config.json</configFile>
      </configuration>
  </plugin>
```
```html
命令有：
mock:help
mock:run
```
服务提供方：
```html
  [{
    "request": {
      "uri": "/hello"
    },
    "response": {
      "text": "hello world."
    }
  },{
    "request": {
      "uri": "/getjson",
      "method": "get"
    },
    "response": {
      "status": 200,
      "headers": {
        "content-type": "application/json"
      },
      "json": {
        "suc": true
      }
    }
    }]
```
esb服务提供方：
-	1.修改dev.properties的esb url为本机端口
-	2.修改config.json文件,如：
```html
	[{
		"request" : {
			"uri" : "/esb",
			"json_paths" : {
				"$.header.serviceCode" : "OMS_S_003201"
			}
		},
		"response" : {
			"status" : 200,
			"header" : {
				"content-type" : "application/json"
			}
			"json" : {
				"id" : 12
			}
		}
	},
	{
	"request" : {
		"uri" : "/esb"
	},
	"response" : {
		"proxy":{
			"url":"http://dev-esb",
			"failover":"src/test/failover.json"
		}
	}
	}
	]
```
