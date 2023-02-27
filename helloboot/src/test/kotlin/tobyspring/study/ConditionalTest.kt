package tobyspring.study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.*
import org.springframework.core.type.AnnotatedTypeMetadata
import kotlin.reflect.jvm.jvmName

class ConditionalTest {

    @Test
    fun conditional() {
        // true
        val contextRunner = ApplicationContextRunner()
        contextRunner.withUserConfiguration(Config1::class.java)
            .run {
                Assertions.assertThat(it).hasSingleBean(MyBean::class.java)
                Assertions.assertThat(it).hasSingleBean(Config1::class.java)
            }

//        val ac = AnnotationConfigApplicationContext()
//        ac.register(Config1::class.java)
//        ac.refresh()
//
//        val myBean = ac.getBean(MyBean::class.java)
//        Assertions.assertThat(myBean).isNotNull


        //false
//        val ac2 = AnnotationConfigApplicationContext()
//        ac2.register(Config2::class.java)
//        ac2.refresh()
//
//        val myBean2 = ac2.getBean(MyBean::class.java)

        val contextRunner2 = ApplicationContextRunner()
        contextRunner2.withUserConfiguration(Config2::class.java)
            .run {
                Assertions.assertThat(it).doesNotHaveBean(MyBean::class.java)
                Assertions.assertThat(it).doesNotHaveBean(Config2::class.java)
            }
    }
}


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Conditional(TrueCondition::class)
annotation class TrueConditional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Conditional(FalseCondition::class)
annotation class FalseConditional


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Conditional(BooleanCondition::class)
annotation class BooleanConditional(val value: Boolean)

//@Configuration
//@Conditional(TrueCondition::class)
//@TrueConditional
@BooleanConditional(true)
class Config1 {

    @Bean
    fun myBean(): MyBean {
        return MyBean()
    }
}

//@Configuration
//@Conditional(FalseCondition::class)
//@FalseConditional
@BooleanConditional(false)

class Config2 {

    @Bean
    fun myBean(): MyBean {
        return MyBean()
    }
}

class MyBean

class TrueCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return true
    }
}

class FalseCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return false
    }
}

class BooleanCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional::class.jvmName)
        println(annotationAttributes)
        println(annotationAttributes?.get("value"))
        return annotationAttributes?.get("value")?.equals(true) ?: false
    }
}
