package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max

object MaxTemperaturesOneDay {
  def parseLine(line:String)= {
    val fields = line.split(",")
    val stationID = fields(0)
    val day = fields(1)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
    (stationID, day, entryType, temperature)
  }
  
  def main(args: Array[String]) {
   
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local[*]", "MaxTemperaturesOneDay")    
    val lines = sc.textFile("../../DataSet/1800.csv")
    
    val parsedLines = lines.map(parseLine)
    val maxTemps = parsedLines.filter(x => x._3 == "TMAX")
    val stationTemps = maxTemps.map(x => (x._2, x._4.toFloat))
    val maxTempsByStation = stationTemps.reduceByKey( (x,y) => max(x,y))
    val results = maxTempsByStation.collect()
    
    var day = ""
    var temp = 0.0
    var formattedTemp = ""
    for (result <- results.sorted) {
      if(temp < result._2){
         day = result._1
         temp = result._2
         formattedTemp = f"$temp%.2f F"
      }      
    }
    println(s"$day max temperature: $formattedTemp")
  }
}