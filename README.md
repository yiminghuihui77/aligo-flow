# Aligo-Flow

## 关于Aligo-Flow你要知道的事
#### aligo-flow致力于解决什么问题？ 
或许，你所在业务部门对外提供一套能力，你们需要对接不同的业务部门或业务线。业务接口开发过程中，无论是由于历史债务问题，还是开发人员流动问题，你们的项目中可能存在相似能力的接口的情况，即下列两种情况：
1. 针对不同的业务线，提供了不同的定制化接口，但接口能力相似
2. 针对同一个领域能力，提供一个接口，但接口中存在大量业务线的if-else判断逻辑

上述两种情况实际上都是脱离了平台化视角，你的服务并没有抽离出核心的领域能力，没有通用与稳定能力的沉淀；对外提供的接口无非是是创建新接口重复造轮子，亦或是在老接口中堆砌新的定制化逻辑。带来的问题显而易见：

* 研发同学捆绑业务线，以垂直业务为分界线，各自为阵
* 新同学学习成本高，需要在海量的相似能力接口中摸索某个业务线的定制化逻辑，难以了解系统的全貌，容易导致改小问题，引发大问题
* 随时间推移，系统持续腐化，提高维护成本
* 没有通用能力的沉淀，难以快速响应业务的变化，以至于新业务线接入时，只能花费大量的人力成本来弥补系统层面的技术问题所带来的团队产能下降。

我们的系统，不应是给业务做定制化逻辑的，这要求我们将思维视角从面向定制转为面向通用；将业务需求迭代的思维转化平台能力沉淀的思维，力达消除重复建设，将系统能力二次赋能！

综上所述，要根治上述问题，除了重构，没有捷径可走。在完整梳理系统接口能力的前提下，我们需要识别出系统稳定的平台能力，抽离并能复用通用能力，对外提供一套标准的接口以屏蔽业务线之间的差异。

#### aligo-flow目前提供什么能力？
* 抽离领域能力，领域能力组件化，提高复用性，降低代码重复率和耦合
* 配置替代编程，通过配置领域能力组件，配置出接口的能力
* 支持异步执行、并行执行、事务模板

#### aligo-flow未来能力的展望?
目前aligo-flow仅支持通过在项目中配置xml形式的流程文件来定义一套能力，并在SpringBoot启动时加载解析xml来识别。  
未来，aligo-flow还将扩充更多的概念，如组件池，我希望能够在Web页面上定义一个新组件，或定义一个新的执行流，执行流与具体的接口路径绑定。流程信息持久化到数据库，提交执行流变更后，程序运行时能够动态的变更一个接口的能力，以快速响应业务述求。

## 接入aligo-flow步骤
详情参考aligo-flow-demo 

1. 前提的环境条件  
   JDK版本：1.8+  
   SpringBoot上下文  

2. application.yml上的基础配置
```json 
aligo:
  flow:
    # 数据源类型
    schema: xsd
  global:
    # 应用id
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