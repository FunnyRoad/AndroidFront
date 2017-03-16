package com.project.application.funnyroad.newroadtrip.model;

import com.project.application.funnyroad.home.model.Departure;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by oraberkane on 16/03/2017.
 */

public class RoadTripSent implements Serializable {

        private int id;
        private String name;
        private Departure departure;
        private String arrival;
        private ArrayList<Integer> places;
        private ArrayList<Integer> guests;
        private int owner;

        public RoadTripSent(String name , int owner, Departure begin, String destination, ArrayList<Integer> listPlace ,
                        ArrayList<Integer> guests ) {
            this.name = name;
            this.owner = owner;
            this.departure = begin;
            this.arrival = destination;
            this.places = listPlace;
            this.guests = guests;
        }


        public RoadTripSent(Departure begin , String destination , String name){
            this.departure = begin;
            this.arrival = destination;
            this.name = name;
        }

        public RoadTripSent(String name , Departure begin, String destination, ArrayList<Integer> listPlace , int owner) {
            this.name = name;
            this.departure = begin;
            this.arrival = destination;
            this.name= name;
            this.places = listPlace;
            this.owner = owner;
        }

        public RoadTripSent(int id , String name , Departure begin, String destination , ArrayList<Integer> places) {
            this.id = id;
            this.name = name;
            this.departure = begin;
            this.arrival = destination;
            this.places = places;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Integer> getPlaces() {
            return places;
        }

        public void setPlaces(ArrayList<Integer> places) {
            this.places = places;
        }

        public ArrayList<Integer> getGuests() {
            return guests;
        }

        public void setGuests(ArrayList<Integer> guests) {
            this.guests = guests;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public Departure getBegin() {
            return departure;
        }

        public void setBegin(Departure begin) {
            this.departure = begin;
        }

        public String getDestination() {
            return arrival;
        }

        public void setDestination(String destination) {
            this.arrival = destination;
        }

        public ArrayList<Integer> getListPlace() {
            return places;
        }

        public void setListPlace(ArrayList<Integer> listPlace) {
            this.places = listPlace;
        }


        @Override
        public String toString() {
            return "RoadTrip{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", places=" + places +
                    ", guests=" + guests +
                    ", owner=" + owner +
                    '}';
        }


}
