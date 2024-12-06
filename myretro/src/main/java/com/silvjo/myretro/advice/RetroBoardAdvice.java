package com.silvjo.myretro.advice;


import com.silvjo.myretro.board.RetroBoard;
import com.silvjo.myretro.exception.RetroBoardNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@Aspect
public class RetroBoardAdvice {
    @Around("execution(* com.silvjo.myretro.presistence.RetroBoardRepository.findById(java.util.UUID))")
    public Object checkFindRetroBoard(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        log.info("[ADVICE] findRetroById");
        Optional<RetroBoard> retroBoard = (Optional<RetroBoard>) proceedingJoinPoint.proceed(new Object[]{
                UUID.fromString(proceedingJoinPoint.getArgs()[0].toString())
        });
        if (retroBoard.isEmpty()){
            throw new RetroBoardNotFoundException();
        }
        return retroBoard;
    }
}

