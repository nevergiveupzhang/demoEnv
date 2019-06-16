package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import com.example.demo.cache.Cached;
import com.example.demo.cache.CachedDataMapZ;


@Aspect
@Component
public class CachedAdvice {
    protected static char chr = '_';
    private CachedDataMapZ<Object> cachedDataMapZ = new CachedDataMapZ<Object>();

    @Pointcut("execution(public * *..*(..))")
    public void cache() {
    }

    /**
     *  环绕通知
     * @param joinPoint ProceedingJoinPoint
     * @param cachedAnnotation 缓存描述
     * @return
     * @throws Throwable
     */
    @Around("cache() && @annotation(cachedAnnotation)")
    public Object addAroundCache(ProceedingJoinPoint joinPoint, Cached cachedAnnotation) throws Throwable {
        String[] key = genKey(joinPoint.getArgs(), cachedAnnotation.cacheDescription(),joinPoint.getSignature());
        Object o = cachedDataMapZ.get(key);
       if(o != null){
           return o;
       }else {
           Object o1 = joinPoint.proceed();
           cachedDataMapZ.setMyData(o1,key);
           return o1;
       }

    }

    /**
     * 生成缓存key
     * @param parames 方法的参数
     * @param cacheDescription 缓存描述
     * @param signature 方法签名
     * @return
     */
    private String[] genKey(Object[] parames, String cacheDescription, Signature signature) {
        if (null == cacheDescription  || signature == null) {
            return new String[]{};
        }
        StringBuilder param = new StringBuilder().append(cacheDescription).append(chr).append(signature.toString()).append(chr);
        for (Object obj : parames) {
            param.append(obj.toString()).append(chr);
        }
        param.deleteCharAt(param.length()-1);
        return param.toString().split(String.valueOf(chr));
    }

}
