package calculator

import akka.actor.Actor
import akka.event.slf4j.SLF4JLogging
//import akka.actor.Props

import spray.routing.{HttpService, RequestContext}
import spray.routing.directives.CachingDirectives
import spray.can.server.Stats
import spray.can.Http
import spray.httpx.marshalling.Marshaller
import spray.httpx.encoding.Gzip
import spray.util._
import spray.http._
//import spray.httpx.unmarshalling._
//import HttpMethods._


class CalculatorServiceActor extends Actor with RestService
{
	def actorRefFactory = context
	def receive = runRoute(restRoute)
	/*
	def receive = { 
		case "test" => println("test response")
		case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
		sender ! HttpResponse(entity = "PONG")
		case _       => println("huh?")
	}
	*/
}

trait RestService extends HttpService {
	implicit def executionContext = actorRefFactory.dispatcher
  	val restRoute = {
  		get
  		{
  			path("ping")
  			{
  				complete(HttpResponse(entity = "vastaus"))
  				//complete("PONG")
  			}
  		}
  		/*
    path("entity") {
      	get { 
        complete(HttpResponse(entity = "vastaus"))
      } ~
      put {
        complete(HttpResponse(entity = "tehty"))
      }
    }
    */
  }
  /*
  def pingRoute = path("ping") {
    get { complete("pong!") }
  }

  def pongRoute = path("pong") {
    get { complete("pong!?") }
  }

  def restRoute = pingRoute ~ pongRoute
  */
}
