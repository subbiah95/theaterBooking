package com.booking.theater.controller;

import com.booking.theater.service.BookingInvalidationWorker;
import com.booking.theater.service.BookingWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Service
public class BookingWorkers {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    @EventListener(ApplicationReadyEvent.class)
    public void startWorkers() {
        System.out.println("startWorkers");

        BookingWorker bookingWorker = applicationContext.getBean(BookingWorker.class);
        taskExecutor.execute(bookingWorker);

        BookingInvalidationWorker bookingInvalidationWorker = applicationContext.getBean(BookingInvalidationWorker.class);
        taskExecutor.execute(bookingInvalidationWorker);
    }
}
