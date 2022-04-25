package com.elotmani.booking.interfaces.eventhandlers;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.elotmani.booking.application.internal.commandservices.CargoBookingCommandService;
import com.elotmani.shareddomain.model.events.CargoHandledEvent;
import com.elotmani.shareddomain.model.events.CargoHandledEventData;


@Service
public class CargoHandledEventHandler {

		@Autowired
       private CargoBookingCommandService cargoBookingCommandService;
        /**
         * Cargo Handled Event handler
         * @param event
         */
        @StreamListener(target = "cargoHandlingChannel")
        public void observeCargoHandledEvent( CargoHandledEvent event) {
            System.out.println("***Cargo Handled Event in booking service****Booking ID =="+event.getCargoHandledEventData().getBookingId());
                CargoHandledEventData eventData = event.getCargoHandledEventData();
                System.out.println(eventData.getBookingId());
                System.out.println(eventData.getHandlingLocation());
                System.out.println(eventData.getHandlingCompletionTime());
                System.out.println(eventData.getHandlingType());
                System.out.println(eventData.getVoyageNumber());
               cargoBookingCommandService.updateDelivery(event);
        }


}
