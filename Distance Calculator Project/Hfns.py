#File:       Hfns.py

#Purpose:    Contains function objects for the A* search method.
#            h_zero returns a zero as a h function
#            h_east_west estimates a distance based on longitude
import math
class H_zero:
    def name( self ):
        return "h=0"
    # End name

    def h( self, pt1, pt2 ):
        return 0
    # End h
# End H_zero

class H_east_west:
    def name( self ):
        return "h=east-west distance"
    # End name

    def h( self, pt1, pt2 ):
        return 8 * abs ( pt1 [ 1 ] - pt2 [ 1 ] )
    # End h
# End H_east_west

class H_north_south:
    def name ( self ):
        return "h=north-south distance"
    # End name

    def h ( self, pt1, pt2 ):
        #print(pt1, pt2)
        return 8 * abs ( pt1 [ 0 ] - pt2 [ 0 ] )
    # End h
# End H_north_south

class H_haversine:
    def name ( self ):
        return "h=haversine distance"
    # End name
    
    def h ( self, pt1, pt2 ):
        dlat  = math.radians ( pt2 [ 0 ] - pt1 [ 0 ] )
        dlong = math.radians ( pt2 [ 1 ] - pt1 [ 1 ] )
        # applying formula
        a = ( pow ( math.sin ( dlat / 2 ) , 2 ) +
            ( pow ( math.sin ( dlong / 2 ),  2) *
              math.cos ( math.radians ( pt1 [ 0 ] ) )  * 
              math.cos ( math.radians ( pt2 [ 0 ] ) ) )
             );
        radius = 6371
        d = 2 * radius * math.asin ( math.sqrt ( abs ( a ) ) )

        return d
    # End h
# End H_haversine
    