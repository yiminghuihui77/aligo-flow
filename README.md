# Aligo-Flow

## 关于Aligo-Flow你要知道的事
#### aligo-flow致力于解决什么问题？
* 抽离领域能力，领域能力组件化，提高复用性，降低代码重复率和耦合
* 配置替代编程，通过组配置领域能力组件，配置出接口的能力
* 支持异步执行、并行执行、事务模板

## 使用步骤
详情参考aligo-flow-demo

1. 前提的环境条件  
   JDK版本：1.8+  
   Spring上下文  

2. application.yml上的基础配置
```json
aligo:
  flow:
    #数据源类型
    schema: xsd
  global:
    #应用id
    appId: AligoFlowDemoApp
    #指定事务模板
    transactionTemplate: flowTransactionTemplate
```

3. resource目录下创建ALIGO-FLO目录，并配置执行流.xml
```xml 
<aresFlow name="flow_demo" version="1"
  xmlns="http://www.aligo.com/schema/ares/flow"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.aligo.com/schema/ares/flow/aligoFlow.xsd">


    <flow name="adjust" identifier="key1:key2:*">
        <step name="PARAM_VALID" priority="0" check="true">
            <stream priority="1">
                <node executableComponent="IdempotentValidator"/>
                <node executableComponent="RequestInfoValidator"/>
            </stream>
        </step>


        <step name="BIZ_PROCESSOR" priority="3" >
            <stream priority="1">
                <node executableComponent="BizCoreProcessor"/>
            </stream>
        </step>

        <step name="AFTER_BIZ_PROCESSOR" priority="4">
            <stream priority="1">
                <node executableComponent="OtherBizProcessor"/>
            </stream>
        </step>
    </flow>

</aresFlow>
```


