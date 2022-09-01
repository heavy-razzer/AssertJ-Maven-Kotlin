import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.testng.annotations.Test

@Aspect
object Aspects {

    @Pointcut("execution(* steps.Steps.*(..))")
    fun testStep() {
    }

    @AfterReturning(pointcut = "testStep()")
    fun logTestStep(joinPoint: JoinPoint) {
        println("Test step performed: ${getMethodName(joinPoint)}")
    }

    @Before("@annotation(org.testng.annotations.Test)")
    fun logTest(joinPoint: JoinPoint) {
        println("Test started: ${getTestName(joinPoint)}")
    }

    @Before("@annotation(io.qase.api.annotation.Step) && execution(* steps.Steps.*(..))")
    fun stepMethod(joinPoint: JoinPoint) {
        println("Perform step method: ${getMethodName(joinPoint)}(): ${getMethodParameters(joinPoint)}")
    }

    private fun getMethodName(joinPoint: JoinPoint): String {
        val methodSignature = joinPoint.signature as MethodSignature
        return methodSignature.name
    }

    private fun getTestName(joinPoint: JoinPoint): String {
        val methodSignature = joinPoint.signature as MethodSignature
        val stepAnnotation = methodSignature.method.getAnnotation(Test::class.java)
        return stepAnnotation.description
    }

    private fun getMethodParameters(joinPoint: JoinPoint): ArrayList<String> {
        val parametersList = arrayListOf<String>()
        for (param in joinPoint.args)
            parametersList.add(param.toString())
        return parametersList
    }
}
