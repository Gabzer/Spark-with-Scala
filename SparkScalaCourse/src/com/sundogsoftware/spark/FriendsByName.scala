package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object FriendsByName {
  
  def parseLine(line: String) = {
      val fields = line.split(",")
      val name = fields(1)
      val numFriends = fields(3).toInt
      (name, numFriends)
  }
  
  def main(args: Array[String]) {
   
    Logger.getLogger("org").setLevel(Level.ERROR)    
    val sc = new SparkContext("local[*]", "FriendsByName")    
    val lines = sc.textFile("../../DataSet/fakefriends.csv")
    
    val rdd = lines.map(parseLine)
    
    val totalsByAge = rdd.mapValues(x => (x, 1)).reduceByKey( (x,y) => (x._1 + y._1, x._2 + y._2))    
    val averagesByAge = totalsByAge.mapValues(x => x._1 / x._2)
    
    val results = averagesByAge.collect()    
    results.sorted.foreach(println)
  }
}