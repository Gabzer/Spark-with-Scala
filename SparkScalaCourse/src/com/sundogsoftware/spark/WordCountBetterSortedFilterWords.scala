package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object WordCountBetterSortedFilterWords {
  
  def main(args: Array[String]) {
    
    val listWords = List("you","to","your","the","a","of","and","that","it","in","is","for","on","are","if","s","i","can","be","as","have","with")//,"t","this","or","will","what","at","my","do","not","about","more","an","up","by","ve")
    /*def filterWord(words: String): String = {
      for(word <- words){
        for(item <- listWords){
          
        }
      }
    }*/
    
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext("local", "WordCountBetterSorted")
    val input = sc.textFile("../../DataSet/book.txt")
    
    val words = input.flatMap(x => x.split("\\W+"))    
    val lowercaseWords = words.map(x => x.toLowerCase())
    
    val wordCounts = lowercaseWords.map(x => (x, 1)).reduceByKey( (x,y) => x + y )
    val wordCountsSorted = wordCounts.map( x => (x._2, x._1) ).sortByKey()
    //val wordCountsSortedFiltered = wordCountsSorted.filter(x => )
    
    for (result <- wordCountsSorted) {
      val count = result._1
      val word = result._2
      println(s"$word: $count")
    }
    
  }
}