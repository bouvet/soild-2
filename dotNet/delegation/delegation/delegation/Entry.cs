namespace delegation.delegation
{
    public class Entry
    {
        private readonly string fromIp;
        private readonly string toIp;
        private readonly string locationFrom;
        private readonly string locationTo;
        private readonly string countryCode;
        private readonly string country;

        public Entry(
                string fromIp,
                string toIp,
                string locationFrom,
                string locationTo,
                string countryCode,
                string country
        )
        {
            this.fromIp = fromIp;
            this.toIp = toIp;
            this.locationFrom = locationFrom;
            this.locationTo = locationTo;
            this.countryCode = countryCode;
            this.country = country;
        }

        public string getFromIp()
        {
            return fromIp;
        }

        public string getToIp()
        {
            return toIp;
        }

        public string getLocationFrom()
        {
            return locationFrom;
        }

        public string getLocationTo()
        {
            return locationTo;
        }

        public string getCountryCode()
        {
            return countryCode;
        }

        public string getCountry()
        {
            return country;
        }
    }

}
