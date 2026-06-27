        package resources;

        public enum APIResources {

            AddPlaceAPI("maps/api/place/add/json"),
            DeletePlaceAPI("maps/api/place/delete/json"),
            GetPlaceAPI("maps/api/place/get/json");

            private String _resource;


            // executed by .valueOf
            APIResources(String resource){
                _resource = resource;
            }

            public String getResource(){
                return _resource;
            }
        }
