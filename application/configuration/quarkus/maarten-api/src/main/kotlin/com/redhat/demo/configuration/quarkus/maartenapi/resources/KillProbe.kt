//package com.redhat.demo.configuration.quarkus.maartenapi.resources
//
//import io.quarkus.runtime.ShutdownEvent
//import jakarta.enterprise.event.Observes
//import jakarta.inject.Inject
//import jakarta.ws.rs.POST
//import jakarta.ws.rs.Path
//
//@Path("/shutdown-hook")
//class KillProbe {
//
//    @Inject
//    lateinit var gracefulShutdownResourcesService: GracefulShutdownResourcesService
//
//    @POST
//    @Path("/trigger") //On API call (e.g., for easy testing)
//    fun trigger() = gracefulShutdown()
//
//    fun onStop(@Observes ev: ShutdownEvent) { //On Quarkus event
//        gracefulShutdown()
//    }
//
//    fun gracefulShutdown(){
//        gracefulShutdownResourcesService
//    }
//}
//
////This class should be put in the runtime-api module
//class GracefulShutdownResourcesService {
//    fun gracefulShutdown(){
//        //... - resource handling
//    }
//}
//
//
//
//
//
//
//
//
//
//
