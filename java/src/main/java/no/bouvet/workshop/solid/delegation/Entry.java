package no.bouvet.workshop.solid.delegation;

public class Entry {

    private final String fromIp;
    private final String toIp;
    private final String locationFrom;
    private final String locationTo;
    private final String countryCode;
    private final String country;

    public Entry(
            String fromIp,
            String toIp,
            String locationFrom,
            String locationTo,
            String countryCode,
            String country
    ) {
        this.fromIp = fromIp;
        this.toIp = toIp;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.countryCode = countryCode;
        this.country = country;
    }

    public String getFromIp() {
        return fromIp;
    }

    public String getToIp() {
        return toIp;
    }

    public String getLocationFrom() {
        return locationFrom;
    }

    public String getLocationTo() {
        return locationTo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }
}
