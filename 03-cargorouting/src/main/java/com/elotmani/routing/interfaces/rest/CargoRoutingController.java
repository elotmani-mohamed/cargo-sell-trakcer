package com.elotmani.routing.interfaces.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elotmani.routing.application.internal.queryservices.CargoRoutingQueryService;
import com.elotmani.routing.domain.model.aggregates.Voyage;
import com.elotmani.routing.domain.model.entities.CarrierMovement;
import com.elotmani.shareddomain.model.TransitEdge;
import com.elotmani.shareddomain.model.TransitPath;

import java.util.ArrayList;
import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping("/cargorouting")
public class CargoRoutingController {


    private CargoRoutingQueryService cargoRoutingQueryService; // Application Service Dependency

    /**
     * Provide the dependencies
     * @param cargoRoutingQueryService
     */
    public CargoRoutingController(CargoRoutingQueryService cargoRoutingQueryService){
        this.cargoRoutingQueryService = cargoRoutingQueryService;
    }


    /**
     *
     * @param originUnLocode
     * @param destinationUnLocode
     * @param deadline
     * @return TransitPath - The optimal route for a Route Specification
     */

    @GetMapping(path = "/optimalRoute")
    @ResponseBody
    public TransitPath findOptimalRoute(
             @RequestParam("origin") String originUnLocode,
             @RequestParam("destination") String destinationUnLocode,
             @RequestParam("deadline") String deadline) {
    	
    	System.err.println("CargoRoutingController.findOptimalRoute()");

        List<Voyage> voyages = cargoRoutingQueryService.findAll();
        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();
        for(Voyage voyage:voyages){

            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber(voyage.getVoyageNumber().getVoyageNumber());
            CarrierMovement movement =
                    ((List<CarrierMovement>)voyage.getSchedule().getCarrierMovements()).get(0);
            transitEdge.setFromDate(movement.getArrivalDate());
            transitEdge.setToDate(movement.getDepartureDate());
            transitEdge.setFromUnLocode(movement.getArrivalLocation().getUnLocCode());
            transitEdge.setToUnLocode(movement.getDepartureLocation().getUnLocCode());
            transitEdges.add(transitEdge);

        }

        transitPath.setTransitEdges(transitEdges);
        return transitPath;

    }
}
