## ����
    Ϊ�������ɻ����Բ���׼�����ȴmock������

## ����
    **esb**
    ϵͳ�����ܹ�����esb����,����ģ�ⷵ��
    **ǰ��**
    ͬʱҲҪ֧��ǰ������
## ʹ��˵��
  <plugin>
      <groupId>com.kxtx</groupId>
      <artifactId>mock-plugin-maven</artifactId>
      <version>1.0-SNAPSHOT</version>
      <configuration>
          <port>8088</port>
          <configFile>${build.testSourceDirectory}\mock\config.json</configFile>
      </configuration>
  </plugin>
  �����ṩ����
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
	esb�����ṩ����
	1.�޸�dev.properties��esb urlΪ�����˿�
	2.�޸�config.json�ļ�,�磺
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