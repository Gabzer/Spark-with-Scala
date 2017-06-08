package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object PurchaseByCustomer {
  
    def parseLine(line: String) = {
        val fields = line.split(",")
        (fields(0).toInt, fields(2).toFloat)
    }
  
    def main(args: Array[String]) {
        Logger.getLogger("org").setLevel(Level.ERROR)
        val sc = new SparkContext("local[*]", "PurchaseByCustomer")
        val input = sc.textFile("../../DataSet/customer-orders.csv")
        
        val mappedInput = input.map(parseLine)
    
        val totalByCustomer = mappedInput.reduceByKey( (x,y) => x + y )
        
        val results = totalByCustomer.collect()
        
        // Print the results.
        results.foreach(println)
        
    }
}