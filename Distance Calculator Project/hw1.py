
# Class:     CSCI 656
# Program:   Assignment 1
# Author:    Dinesh Kolla
# Z-number:  z1935563
# Date Due:  03/04/22
# Purpose:   A*
# Execution: python3 hw1.py

import sys
from Node import Node
from Hfns import H_zero, H_east_west, H_north_south, H_haversine
from Data import Data

def plain_city_string( city_list ):
    base_city_string = " ".join ( [ city for city in city_list ] )
    full_city_string = "\n\t" + base_city_string
    return full_city_string 
# End plain_city_string 

def city_string ( node_list ):
    base_city_string = "\n\t".join ( [ node.name for node in node_list ] )
    full_city_string = "\n\t" + base_city_string
    return full_city_string 
# End city_string  

def city_f_string( node_list ):
    base_city_string = "\n\t".join ( [ node.name + "\t" * ( 2 - ( 1 if len ( node.name ) >= 8 else 0 ) ) + str ( node.f ) for node in node_list ] ) 
    full_city_string = "\n\t" + base_city_string
    return full_city_string
# End city_f_string

# Function: astar(from_city, to_city, franceroads, france_long, h)
#
# Inputs:  from_city:    departure city
#          to_city:      destination city
#          france_roads: road list
#          france_long:  city longititude
#          h:            h function used in search
#
# Outputs:  None           
#
# Notes:    Look for shortest path between two cities using A* search.

def astar ( from_city , to_city, france_roads, h ):
    found_path     = False
    open_list      = [ from_city ]
    closed_list    = [ ]
    nodes_expanded = 0
    path_length    = 0

    # set inital cities f, g and h values
    from_city.h = h.h ( ( Data.france_latlong [ to_city.name ] [ "lat" ] , Data.france_latlong [ to_city.name ] [ "long" ] ) , 
    ( Data.france_latlong [ from_city.name ] [ "lat" ] , Data.france_latlong [ from_city.name ] [ "long" ] ) )

    from_city.f = from_city.h + from_city.g

    print ( "-" * 10 + "A* with ", h.name( ), " between {} -> {}". format ( from_city.name,to_city.name ) , ( "-" * 10 ) + "\n\n", sep='' )

    # while open list is not empty
    while len( open_list ) != 0:
        # pop front of openlist and set current node
        current_node = open_list.pop ( 0 )
        print( "Expanding ", current_node.name, " f=", current_node.f, ",",
              " g=", current_node.g, ",", " h=", current_node.h, sep='' )        
        # if current node is destination, set path length and break
        if current_node.name == to_city.name:
            path_length = current_node.f
            found_path  = True
            break
        #End if
        nodes_expanded += 1
        
        # get current node's children and calculate their f, g and h values
        # sort by name and print
        
        children = [ Node ( name, current_node.name,
                          int( france_roads [ current_node.name ] [ name ] ) + current_node.g,
                          h.h ( ( Data.france_latlong [ to_city.name ] [ "lat" ], Data.france_latlong [ to_city.name ] [ "long" ] ),
                              ( Data.france_latlong [ name ][ "lat" ], Data.france_latlong [ name ] [ "long" ] ) ) ) 
                              for name in france_roads [ current_node.name ].keys( ) ]
        children = sorted ( children, key = lambda x: x.name )
        print ( "Children are: " + city_string ( children ) )

        # for every child
        for child in children:
            if child not in closed_list:
                if child not in open_list:
                    # add to open list
                    open_list.append ( child )

                # else if child has smaller value then openlist, replace openlist city with child
                elif child.f < open_list [ open_list.index ( child ) ] .f:
                    print( "***Revaluing open node", child.name, "from",
                          open_list[open_list.index ( child ) ] .f, "to", child.f ) 
                    open_list [ open_list.index ( child ) ] = child
                # End if-else
            
            # else if child has smaller value then closed_list,
            # remove from closed_list and add child back onto open_list
            elif child.f < closed_list [ closed_list.index ( child ) ] .f:
                print ( "***Revaluing closed node", child.name, "from",
                      closed_list [ closed_list.index ( child ) ] .f, "to", child.f )
                open_list.append ( child )
                closed_list.remove ( child )
            # End if-else
        # End for
        # sort openlist by name and by f value and print
        open_list = sorted ( open_list, key = lambda x: x.name )
        open_list = sorted ( open_list, key = lambda x: x.f )
        print ( "Open list is: ", city_f_string ( open_list ) )

        # add current node to closed list and print
        closed_list.append ( current_node )
        print ( "Closed list is: ", city_f_string ( closed_list ) )
        print ( )
    # End While

    # if solution found
    #  backtrack through the closed list using parent references to
    #  construct the full path

    if found_path:
        solution_path = [ ]
        while current_node != from_city:
            solution_path.insert ( 0, current_node.name )
            current_node = closed_list [ closed_list.index ( Node ( current_node.parent ) ) ]
        solution_path.insert ( 0, from_city.name )
        print ( "\n\nA* solution with ", h.name( ), ": ", plain_city_string ( solution_path ) , sep='' )
        print ( "Path length:", path_length )
    else:
        print ( "\n\nA* has no solution" )
        return 0
    # End if-else  
    print ( nodes_expanded, "nodes expanded\n\n" )
    return nodes_expanded
# End astar

france_roads      = { }
#france_roads_file = open( "france-roads1.txt" )
france_roads_file = open(r"/home/turing/t90rkf1/d656/dhw/hw1-astar/france-roads1.txt")
for input_line in france_roads_file:
    input_line = input_line.strip( )

    if input_line != "" and input_line [ 0 ] != "#":

        # ':' indicates new city, make new inner dict
        if ":" in input_line:
            cur_city = input_line.replace ( ":", "" ).lower( ).capitalize( )
            france_roads [ cur_city ] = { }
        else:
            city_distance = input_line.split( )
            france_roads [ cur_city ] [ city_distance [ 0 ] ] = city_distance[ 1 ]
        # End if-else
    # End if
# End for
# close file
france_roads_file.close( )
Data.france_latlong = Data.read_latlong_file( )
from_city = 'Calais'
to_cities = ('Bordeaux', 'Toulouse', 'Montpellier', 'Avignon', 'Marseille', 'Nice', 'Grenoble')
h_zero_nodes_expanded,h_east_west_nodes_expanded,h_north_south_nodes_expanded,h_haversine_nodes_expanded = [ ],[ ],[ ],[ ]
for to_city in to_cities:
    h_zero_nodes_expanded.append(astar(Node(from_city), Node(to_city), france_roads, H_zero()))
    h_east_west_nodes_expanded.append(astar(Node(from_city), Node(to_city), france_roads, H_east_west()))
    h_north_south_nodes_expanded.append(astar(Node(from_city), Node(to_city), france_roads, H_north_south()))
    h_haversine_nodes_expanded.append(astar(Node(from_city), Node(to_city), france_roads, H_haversine()))
# End for
print("Algorithms >\th_zero\th_east_west\tH_north_south\tH_haversine")
print( "{} to".format( from_city ) )
for i,city in enumerate( to_cities ):
    print( str ( city ) + "\t" * ( 2 - ( 1 if len ( city ) > 7 else 0 ) ) + str ( h_zero_nodes_expanded [ i ] )
     + "\t" + str ( h_east_west_nodes_expanded [ i ] ) + "\t\t" + str ( h_north_south_nodes_expanded [ i ] ) + "\t\t" + str ( h_haversine_nodes_expanded [ i ] ) )
# End for
