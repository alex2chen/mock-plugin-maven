## 背景
    为后续集成化测试测试准备，先搭建mock服务器

## 需求
    **esb**
    系统间解耦，能够代理esb请求,进行模拟返回
    **前端**
    同时也要支持前端请求
## 使用说明
  <plugin>
      <groupId>com.kxtx</groupId>
      <artifactId>mock-plugin-maven</artifactId>
      <version>1.0-SNAPSHOT</version>
      <configuration>
          <port>8088</port>
          <configFile>${build.testSourceDirectory}\mock\config.json</configFile>
      </configuration>
  </plugin>
  服务提供方：
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
	esb服务提供方：
	1.修改dev.properties的esb url为本机端口
	2.修改config.json文件,如：
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